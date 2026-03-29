# ⚠️ IMPORTANT: How to Open This Project in Android Studio

## Correct Folder to Open

**OPEN THIS FOLDER:**
```
/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android
```

## DO NOT OPEN THESE FOLDERS

❌ `/media/barathvikraman/New Volume/Projects/Murugaproxi/` (root)
❌ `/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend` (frontend folder)

## Step-by-Step Instructions

1. **Close Android Studio** if it's already open

2. **Open Android Studio**

3. **Click File → Open**

4. **Navigate to:**
   ```
   /media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android
   ```

5. **Click "Select Folder" or "Open"**

6. **Wait for Gradle sync to complete** (you'll see progress at the bottom right)

7. **Once synced, go to Build → Build Bundle(s)/APK(s) → Build APK(s)**

## If You Get Errors

### Error: "Could not read build file '/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/app/build.gradle' as it does not exist"

**Solution:** You opened the wrong folder. Close this project and open:
```
/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android
```

### Error: "Could not get unknown property 'release' for SoftwareComponent container"

**Solution:** Clear Gradle cache:
1. Go File → Invalidate Caches
2. Click "Invalidate and Restart"
3. Let Android Studio restart
4. Try building again

## Project Path is Long?

If the path is too long in Windows/File Dialog, you can:
1. Copy the path to clipboard
2. Use Mac/Linux terminal to navigate to the folder
3. Run: `open .` (macOS) or `explorer .` (Windows)
4. Then select the folder in the dialog that opens

## Building from Terminal

```bash
cd "/media/barathvikraman/New Volume/Projects/Murugaproxi/frontend/android"
./gradlew assembleDebug
```

APK will be at: `app/build/outputs/apk/debug/app-debug.apk`
