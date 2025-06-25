import express from "express";
import fetch from "node-fetch";
import dotenv from "dotenv";
dotenv.config();

const app = express();
app.use(express.json());

app.post("/getHint", async (req, res) => {
  const { prompt } = req.body;
  console.log("User Prompt:", prompt);

  try {
    const response = await fetch("https://api.groq.com/openai/v1/chat/completions", {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${process.env.GROQ_API_KEY}`,
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        model: "llama3-70b-8192",
        messages: [
          { role: "system", content: "You are a helpful video game assistant." },
          { role: "user", content: prompt }
        ]
      })
    });

    const data = await response.json();
    console.log("Groq Response:", data);
    res.json({ result: data.choices?.[0]?.message?.content || "No response from model." });

  } catch (err) {
    console.error("Error fetching from Groq:", err);
    res.status(500).json({ error: "Server error or invalid API call." });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));