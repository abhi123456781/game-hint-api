# Game Hint AI

Game Hint AI is an iOS app that uses artificial intelligence to help players get unstuck in video games. Users can ask questions like "How do I unlock the Webgliders in Marvel Spider-Man 2?" and get intelligent, game-specific hints in real-time.

## Platforms

- `ios/` – SwiftUI frontend for iPhone
- `android/` – Native Kotlin frontend for Android (WIP or working)
- `backend/` – Node.js API hosted on Render

## Features

- Chat-based AI assistant trained on game logic
- Chat memory: AI remembers the conversation
- Native iOS frontend built in SwiftUI
- Native Android frontend built in Kotlin with Jetpack Compose 
- Node.js + Express backend hosted on Render
- Powered by Groq's LLaMA 3 model
- Secure API key storage with `.env`

## Why I Built This

I built Game Hint AI to combine my passions for gaming and computer science. Instead of searching forums or watching long videos, players can now get help instantly using AI. I created both the backend and frontend myself to learn full-stack development and deploy a real-world tool. I wanted to publish this on the App Store, but it costs too much money. I am now learning how to make this on Android Studio.

## Technologies Used

Backend:
	•	Node.js – JavaScript runtime for the server
	•	Express.js – Lightweight web framework for building the API
	•	Groq API – AI model backend powering game hints
	•	Render – Cloud hosting for backend deployment

Frontend (iOS):
	•	SwiftUI – Declarative UI framework for native iOS
	•	URLSession – Networking for calling the backend API
	•	Xcode – Development environment for iOS

Frontend (Android):
	•	Kotlin – Language for building Android apps
	•	Jetpack Compose – Modern Android UI toolkit
	•	Coroutines – Asynchronous API calls without blocking UI
	•	Android Studio – Official IDE for Android development
  
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

### Frontend (Android/Google Play Store)

1. Open the Android project:
   ```
   cd android
   ```

2. Open the project in Android Studio and let it finish syncing.

3. Run the app:
   - On a real Android phone (with USB debugging enabled), or  
   - On an emulator (e.g. Pixel 6 API 33)

4. Ask for a game hint in the app — the frontend will send your prompt to the backend API.

 **Coming soon to the Google Play Store** for easier access and installation.

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

## Contact

Feel free to reach out via GitHub or email if you'd like to collaborate or discuss this project further.
