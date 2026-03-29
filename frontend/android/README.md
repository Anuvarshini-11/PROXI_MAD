# ProxiStudy Android App - Native Java Implementation

This is a simple native Android application built in Java (not Expo/React Native).

## Project Structure

```
frontend/android/
├── app/
│   ├── src/main/
│   │   ├── java/com/muruga/proxistudy/
│   │   │   ├── LoginActivity.java
│   │   │   ├── HomeActivity.java
│   │   │   ├── CreateSessionActivity.java
│   │   │   ├── ViewSessionsActivity.java
│   │   │   ├── FeedbackActivity.java
│   │   │   ├── Session.java
│   │   │   └── SessionManager.java
│   │   ├── res/
│   │   │   ├── layout/ (XML layout files for each activity)
│   │   │   ├── drawable/ (UI drawable resources)
│   │   │   └── values/ (colors, strings)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── local.properties
```

## Features

1. **Login Screen** - Email/Password login (no backend auth needed)
2. **Home Screen** - Navigation to all features
3. **Create Session** - Add new study sessions with name, subject, location, participant count
4. **View Sessions** - List all sessions with join button
5. **Feedback Screen** - Submit feedback
6. **Session Management** - In-memory storage using ArrayList

## How to Build APK

### Method 1: Using Android Studio (Recommended)

1. Open Android Studio
2. Go to **File → Open**
3. Navigate to `/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android`
4. Select the `android` folder and click OK
5. Wait for Gradle sync to complete
6. Go to **Build → Build Bundle(s)/APK(s) → Build APK(s)**
7. The APK will be saved at: `frontend/android/app/build/outputs/apk/debug/app-debug.apk`

### Method 2: Using Command Line

```bash
cd "/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android"

# Build debug APK
./gradlew assembleDebug

# Or build release APK
./gradlew assembleRelease
```

The APK output location:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

## App Flow

```
LoginActivity (Landing Screen)
    ↓ (Login with email/password)
HomeActivity (Main Menu)
    ├→ CreateSessionActivity
    ├→ ViewSessionsActivity
    ├→ FeedbackActivity
    └→ LogoutActivity (returns to LoginActivity)
```

## Technical Details

- **Language**: Java
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Architecture**: Activity-based with Intent navigation
- **Data Storage**: ArrayList (in-memory)
- **UI Framework**: AndroidX AppCompat

## Dependencies

- androidx.appcompat:appcompat:1.6.1
- androidx.constraintlayout:constraintlayout:2.1.4
- com.google.android.material:material:1.9.0
- com.squareup.okhttp3:okhttp:4.11.0

## Backend Integration

The app is configured to work with your backend at:
```
https://proxi-mad.onrender.com/api
```

To integrate API calls with the backend, you can add HTTP calls using OkHttp3 in the Activities.

## Testing the App

1. Build and run on Android emulator or physical device
2. Login with any email/password
3. Navigate through all screens
4. Create sessions
5. Join available sessions
6. Submit feedback

## Notes

- All session data is stored in memory (ArrayList) and will be lost when app closes
- No database or SharedPreferences used (keep it simple for college project)
- UI is intentionally simple and clean
- No backend API calls implemented yet (can be added later)
