QUICK CITY ANDROID PROJECT

This is an Android Studio project that wraps the two QUICK CITY HTML game editions
inside an Android WebView app. It is source code, not a compiled APK.

PROJECT CONTENTS
- app/src/main/assets/offline.html : self-contained offline browser-game edition
- app/src/main/assets/online.html  : 3D browser-game edition (loads Three.js from CDN)
- Android launcher with buttons for Offline and Online 3D modes

BUILD AN APK
1. Install Android Studio on a computer.
2. Open this QUICK_CITY_Android_Project folder in Android Studio.
3. Allow Android Studio to sync/download the Android Gradle Plugin and SDK.
4. Select Build > Build Bundle(s) / APK(s) > Build APK(s).
5. When the build finishes, use the APK location shown by Android Studio.
6. For sharing publicly, use Build > Generate Signed Bundle / APK and create a
   release signing key. Keep that key private and backed up.

NOTES
- Offline mode stores its game HTML inside the app and does not need internet.
- Online 3D mode requires internet because the game downloads the Three.js engine
  from a CDN when opened.
- This prototype is a simple WebView wrapper, not a full native 3D game.
- I cannot produce a verified/installable APK in this environment because the
  Android SDK/Gradle build toolchain is not installed here.

PHONE-ONLY CLOUD BUILD
This project includes a GitHub Actions workflow at .github/workflows/build-apk.yml.
Upload the contents of this folder to a new GitHub repository (main branch).
GitHub Actions will build a debug APK. Open the repository's Actions tab, open
"Build QUICK CITY APK", then download the "QUICK-CITY-debug-APK" artifact.
The downloaded artifact is a ZIP containing app-debug.apk. Extract it and install
app-debug.apk on your Android phone. You may need to allow installs from your
browser or file manager. This is a debug/test APK, not a Play Store release.
