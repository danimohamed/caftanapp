@echo off
echo ============================================
echo Caftan Rental App - Quick Rebuild
echo ============================================
echo.

echo Step 1: Stopping Gradle daemon...
call gradlew --stop

echo.
echo Step 2: Cleaning project...
call gradlew clean

echo.
echo Step 3: Building debug APK...
call gradlew assembleDebug

echo.
echo ============================================
if %ERRORLEVEL% EQU 0 (
    echo ✓ Build completed successfully!
    echo APK location: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo ✗ Build failed! Check the errors above.
)
echo ============================================
echo.
pause

