import express from "express";
import dotenv from "dotenv";
import fetch from "node-fetch";
dotenv.config();

const app = express();
app.use(express.json());

app.get("/", (req, res) => {
  res.send("✅ Game hint API is running.");
});

app.post("/getHint", async (req, res) => {
  const { messages } = req.body;

  if (!messages || !Array.isArray(messages)) {
    return res.status(400).json({ error: "Prompt missing or invalid message format" });
  }

  try {
    const controller = new AbortController();
const timeout = setTimeout(() => controller.abort(), 15000); // 15s timeout

try {
  const response = await fetch("https://api.groq.com/openai/v1/chat/completions", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${process.env.GROQ_API_KEY}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      model: "llama3-70b-8192",
      messages
    }),
    signal: controller.signal
  });

  clearTimeout(timeout);

  const data = await response.json();
  console.log("✅ Groq response:", data);

  res.json({
    result: data.choices?.[0]?.message?.content ?? "No valid response from AI"
  });

} catch (err) {
  clearTimeout(timeout);
  console.error("❌ Error calling Groq:", err);
  res.status(500).json({ error: "Server error while calling Groq" });
}

    const data = await response.json();

    res.json({
      result: data.choices?.[0]?.message?.content ?? "No valid response from AI"
    });
  } catch (err) {
    console.error("Error calling Groq:", err);
    res.status(500).json({ error: "Server error while calling Groq" });
  }
});
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`✅ Server running on port ${PORT}`));