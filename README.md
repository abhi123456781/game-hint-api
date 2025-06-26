# Game Hint AI

Game Hint AI is an iOS app that uses artificial intelligence to help players get unstuck in video games. Users can ask questions like "How do I beat the Valkyrie Queen in God of War?" and get intelligent, game-specific hints in real-time.

## Features

- Chat-based AI assistant trained on game logic
- Chat memory: AI remembers the conversation
- Native iOS frontend built in SwiftUI
- Node.js + Express backend hosted on Render
- Powered by Groq's LLaMA 3 model
- Secure API key storage with `.env`

## Why I Built This

I built Game Hint AI to combine my passions for gaming and computer science. Instead of searching forums or watching long videos, players can now get help instantly using AI. I created both the backend and frontend myself to learn full-stack development and deploy a real-world tool. I wanted to publish this on the App Store, but it costs too much money.

## Technologies Used

- SwiftUI – frontend interface
- Node.js + Express – backend API
- Groq API – AI chat completion model
- Render – backend hosting
- GitHub – version control and visibility
  
## Project Structure

```
.
├── index.js               # Backend entry point
├── package.json           # Node.js dependencies
├── ios/                   # SwiftUI frontend (iOS app)
│   └── GameHelperAI.xcodeproj
└── README.md
```

## How to Run the App

### Backend (Node.js)

1. Clone the repo:
   ```
   git clone https://github.com/yourusername/game-hint-ai.git
   cd game-hint-ai
   ```

2. Create a `.env` file:
   ```
   GROQ_API_KEY=your_actual_key_here
   ```

3. Install and run:
   ```
   npm install
   node index.js
   ```

Or access the hosted backend: `https://game-hint-api-kiyw.onrender.com/getHint`

### Frontend (iOS App)

1. Open the Xcode project:
   ```
   cd ios
   open GameHelperAI.xcodeproj
   ```

2. Run in Simulator or on a real device (you may need to sign with your Apple ID)

## How It Works

The app uses Groq's hosted LLaMA 3 model via chat API. It maintains conversation context by sending the full message history to the backend.

Example request:

```json
{
  "messages": [
    { "role": "user", "content": "I'm stuck in Elden Ring" },
    { "role": "assistant", "content": "Where exactly are you stuck?" }
  ]
}
```

## Why I Built This

I built Game Hint AI to combine my passions for gaming and computer science. Instead of searching forums or watching long videos, players can now get help instantly using AI. I created both the backend and frontend myself to learn full-stack development and deploy a real-world tool. I wanted to publish this on the App Store, but it costs too much money.

## Technologies Used

- SwiftUI – frontend interface
- Node.js + Express – backend API
- Groq API – AI chat completion model
- Render – backend hosting
- GitHub – version control and visibility

## Contact

Feel free to reach out via GitHub or email if you'd like to collaborate or discuss this project further.
