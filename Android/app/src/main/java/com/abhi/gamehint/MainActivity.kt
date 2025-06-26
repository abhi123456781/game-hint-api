package com.abhi.gamehint

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameHintUI()
        }
    }
}

@Composable
fun GameHintUI() {
    var userInput by remember { mutableStateOf(TextFieldValue("")) }
    val messages = remember { mutableStateListOf<Pair<String, String>>() } // role, content
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize().background(Color(0xFF121212)),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messages) { (role, content) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        contentAlignment = if (role == "user") Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Text(
                            text = content,
                            color = if (role == "user") Color.White else Color.Green,
                            modifier = Modifier
                                .background(if (role == "user") Color(0xFF1E88E5) else Color(0xFF333333))
                                .padding(12.dp)
                                .widthIn(max = 300.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    enabled = !isLoading,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask for a game tip") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val inputText = userInput.text.trim()
                        if (inputText.isNotEmpty()) {
                            messages.add("user" to inputText)
                            userInput = TextFieldValue("")
                            isLoading = true
                            coroutineScope.launch {
                                val result = fetchHintFromServer(messages)
                                if (result != null) {
                                    messages.add("assistant" to result)
                                }
                                isLoading = false
                            }
                        }
                    },
                    enabled = !isLoading
                ) {
                    Text(if (isLoading) "..." else "Send")
                }
            }
        }
    }
}

suspend fun fetchHintFromServer(messages: List<Pair<String, String>>): String? {
    return withContext(Dispatchers.IO) {
        try {
            Log.d("GameHint", "Start network call...")
            val url = URL("https://game-hint-api-kiyw.onrender.com/getHint")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val jsonMessages = messages.map { mapOf("role" to it.first, "content" to it.second) }
            val requestBody = JSONObject(mapOf("messages" to jsonMessages)).toString()

            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            val response = connection.inputStream.bufferedReader().readText()
            val responseJson = JSONObject(response)
            Log.d("GameHint", "Done with network call: $response")
            responseJson.getString("result")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}