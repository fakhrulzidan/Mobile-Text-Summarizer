
# Text Summarizer Mobile App (Android + Kotlin)

This repository contains an Android application that connects to the **Text Summarizer API** to provide users with fast and simple text summarization directly from their phone.

---

## Features
- Input any text and get a summarized version instantly
- Built with **Jetpack Compose** for a modern UI
- Uses **Retrofit** for network requests
- Integrates with backend (Python Flask API)

---

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Retrofit 2 + Gson**
- **Coroutines**

---

## Project Structure (Simplified)
```
app/
├── java/com/belajar/text_summarizer/
│   ├── MainActivity.kt        # Entry point
│   ├── Interface.kt           # Jetpack Compose UI
│   ├── network/RetrofitClient.kt
│   ├── network/ApiService.kt
│   └── model/Dataclass.kt     # SummaryRequest & SummaryResponse
└── res/                       # Colors, themes, etc.
```

---

## Setup & Run

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/text-summarizer-android.git
```

### 2. Open in Android Studio
- Use Android Studio Flamingo or newer  
- Sync Gradle when prompted

### 3. Update API Base URL
In `RetrofitClient.kt`, set your API endpoint:
```kotlin
private const val BASE_URL = "https://<your-ngrok-url>/"
```

Make sure the backend (Python Flask API) is running and accessible.

### 4. Run the App
Select an emulator or connect a physical device and click **Run**.

---

## How to Use
1. Enter text in the input field
2. Tap **Summarize**
3. Wait for the summary to be generated and displayed in the result box

---

## Screenshots
*(Optional: Add screenshots of your app UI here for better presentation)*

---

## Backend Requirement
This mobile app requires the **Text Summarizer API** to be running.  
You can run it locally and expose it via [ngrok](https://ngrok.com) or deploy it online.

---
