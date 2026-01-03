# 📱 Caftan Rental - Android Application

Professional Android application for renting traditional Moroccan caftans, built with Java and Material Design.

## 📋 Overview

This is the **frontend mobile application** that connects to the Laravel backend API. Users can browse caftans, view details, rent items, and manage their rentals through an intuitive and modern interface.

## 🎯 Key Features

### ✨ User Interface
- **Welcome Screen**: Professional splash screen with app branding
- **Material Design 3**: Modern, clean, and responsive UI
- **RecyclerView**: Smooth scrolling lists with efficient memory usage
- **Image Loading**: Fast image caching with Glide library
- **Responsive Layouts**: Works on phones and tablets
- **Status Bar Integration**: Proper toolbar alignment

### 🛍️ Shopping Features
- **Browse Caftans**: Grid view of all available caftans
- **Real-time Availability**: Color-coded badges (Green = Available, Red = Rented)
- **Filter by Size**: S, M, L, XL, or view All
- **Sort by Price**: Low to High or High to Low
- **Detailed View**: Large images with complete information
- **Stock Status**: Clear "In Stock" or "Out of Stock" indicators

### 📅 Rental Management
- **Date Picker Calendars**: Easy date selection (prevents past dates)
- **Auto-formatted Dates**: YYYY-MM-DD format
- **Form Validation**: Ensures all required fields are filled
- **My Rentals**: View all your active rentals
- **Delete Rentals**: Cancel rentals with confirmation dialog
- **Empty States**: User-friendly messages when no data

### 🔄 User Experience
- **Pull to Refresh**: Swipe down to reload caftan list
- **Loading States**: Progress indicators during API calls
- **Error Handling**: Clear error messages with retry options
- **Navigation**: Intuitive back navigation and menu
- **Smooth Animations**: Professional transitions between screens

## 🏗️ Architecture

### Application Structure
```
app/src/main/
├── java/com/example/frontend/
│   ├── WelcomeActivity.java          # Splash/Welcome screen
│   ├── MainActivity.java             # Home screen (caftan list)
│   ├── DetailsActivity.java          # Caftan details screen
│   ├── RentActivity.java             # Rental form screen
│   ├── MyRentalsActivity.java        # My rentals screen
│   │
│   ├── adapters/
│   │   ├── CaftanAdapter.java        # Caftan RecyclerView adapter
│   │   └── RentalAdapter.java        # Rental RecyclerView adapter
│   │
│   ├── api/
│   │   ├── ApiClient.java            # Retrofit singleton client
│   │   └── ApiService.java           # API endpoint definitions
│   │
│   └── models/
│       ├── Caftan.java               # Caftan data model
│       ├── Rental.java               # Rental data model
│       ├── CaftanResponse.java       # API response wrapper
│       ├── RentalResponse.java       # Rental API response
│       └── RentalListResponse.java   # Rental list response
│
├── res/
│   ├── layout/                       # XML layouts for all screens
│   ├── drawable/                     # Icons, backgrounds, shapes
│   ├── values/                       # Colors, strings, themes
│   └── menu/                         # Menu resources
│
└── AndroidManifest.xml               # App configuration
```

## 🛠️ Technical Specifications

### Android Configuration
- **Package Name**: `com.example.frontend`
- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 36 (Android 14)
- **Compile SDK**: 36
- **Version Code**: 1
- **Version Name**: 1.0

### Build Configuration
- **Java Version**: 11
- **Build Tools**: Gradle 8.7
- **View Binding**: Enabled
- **ProGuard**: Configured for release builds

### Dependencies

#### Core Android
```kotlin
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.11.0")
implementation("androidx.activity:activity:1.8.2")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
```

#### Networking
```kotlin
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.google.code.gson:gson:2.10.1")
```

#### UI Components
```kotlin
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("androidx.cardview:cardview:1.0.0")
```

#### Image Loading
```kotlin
implementation("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
```

#### Testing
```kotlin
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
```

## 🚀 Setup & Installation

### Prerequisites
- Android Studio (latest version recommended)
- JDK 11 or higher
- Android SDK with API 24+
- Backend API running (see backend/README.md)

### Installation Steps

1. **Open Project**
   ```bash
   # Open Android Studio
   # Select "Open an Existing Project"
   # Navigate to the root folder (caftanapp)
   # Wait for Gradle sync to complete
   ```

2. **Configure API Endpoint**
   
   Edit `app/src/main/java/com/example/frontend/api/ApiClient.java`:
   
   ```java
   public class ApiClient {
       // Choose based on your setup:
       
       // For Android Emulator:
       private static final String BASE_URL = "http://10.0.2.2:8000/api/";
       
       // For Real Device (replace with your computer's IP):
       private static final String BASE_URL = "http://192.168.1.100:8000/api/";
       
       // For Production:
       private static final String BASE_URL = "https://your-domain.com/api/";
   }
   ```

3. **Sync Gradle**
   - Android Studio should auto-sync
   - Or manually: `File → Sync Project with Gradle Files`

4. **Build Project**
   ```bash
   # From terminal in project root:
   ./gradlew clean
   ./gradlew build
   
   # Or use Android Studio:
   Build → Rebuild Project
   ```

5. **Run Application**
   - Connect Android device or start emulator
   - Click "Run" button (green play icon)
   - Or use `Shift + F10`

### Finding Your Local IP Address

**Windows:**
```cmd
ipconfig
# Look for "IPv4 Address" under your network adapter
```

**Mac/Linux:**
```bash
ifconfig
# Look for inet address under your active network interface
```

**Example**: If your IP is `192.168.1.100`, use:
```java
private static final String BASE_URL = "http://192.168.1.100:8000/api/";
```

## 📱 Screens Overview

### 1. WelcomeActivity (Splash Screen)
- **Purpose**: App branding and smooth entry point
- **Features**:
  - Logo animation
  - App name and subtitle
  - "Get Started" button
  - Full-screen immersive mode
- **Navigation**: Auto-navigates to MainActivity

### 2. MainActivity (Home Screen)
- **Purpose**: Browse all caftans
- **Features**:
  - RecyclerView with all caftans
  - Availability badges (Available/Rented)
  - Menu with "My Rentals" and "Refresh"
  - Filter by size dialog
  - Sort by price dialog
  - Pull-to-refresh
  - Click caftan → DetailsActivity
- **Data Source**: `GET /api/caftans`

### 3. DetailsActivity (Caftan Details)
- **Purpose**: Show complete caftan information
- **Features**:
  - Large caftan image
  - Name, size, price
  - Stock status (In Stock / Out of Stock)
  - "Rent Now" button (disabled if out of stock)
  - Back navigation
  - Share button (optional)
- **Data Source**: Passed via Intent from MainActivity
- **Navigation**: Click "Rent Now" → RentActivity

### 4. RentActivity (Rental Form)
- **Purpose**: Create a new rental
- **Features**:
  - Customer name input
  - Phone number input
  - Start date picker (calendar)
  - End date picker (calendar)
  - Auto-formatted dates (YYYY-MM-DD)
  - Prevents past dates
  - Form validation
  - "Submit Rental" button
- **Data Source**: `POST /api/rentals`
- **Navigation**: Success → Back to MainActivity

### 5. MyRentalsActivity (Rental List)
- **Purpose**: View and manage user's rentals
- **Features**:
  - RecyclerView of all rentals
  - Shows caftan image, name, dates
  - Delete button on each rental
  - Confirmation dialog before delete
  - Empty state message
  - Pull-to-refresh
- **Data Source**: `GET /api/rentals`, `DELETE /api/rentals/{id}`
- **Navigation**: Accessible from MainActivity menu

## 🎨 UI Components

### Material Design Elements
- **MaterialButton**: Rounded, colored buttons
- **MaterialCardView**: Elevated cards with rounded corners
- **TextInputLayout**: Floating label text fields
- **MaterialAlertDialog**: Modern alert dialogs
- **ProgressBar**: Circular loading indicators
- **MaterialToolbar**: App bar with menu

### Color Scheme
```xml
<!-- Primary Brand Color -->
<color name="purple_500">#FF6200EE</color>
<color name="purple_700">#FF3700B3</color>

<!-- Accent Color -->
<color name="teal_200">#FF03DAC5</color>
<color name="teal_700">#FF018786</color>

<!-- Status Colors -->
<color name="green">#4CAF50</color>      <!-- Available -->
<color name="red">#F44336</color>        <!-- Rented -->
<color name="orange">#FF9800</color>     <!-- Warning -->

<!-- Neutral Colors -->
<color name="white">#FFFFFFFF</color>
<color name="black">#FF000000</color>
<color name="gray">#9E9E9E</color>
```

### Typography
- **App Title**: 20sp, Bold
- **Card Title**: 18sp, Bold
- **Body Text**: 14sp, Regular
- **Price**: 16sp, Bold, Primary Color
- **Badges**: 12sp, Bold

## 🔧 API Integration

### Retrofit Setup
```java
public class ApiClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
```

### API Service Interface
```java
public interface ApiService {
    @GET("caftans")
    Call<CaftanResponse> getAllCaftans();

    @GET("caftans/{id}")
    Call<Caftan> getCaftan(@Path("id") int id);

    @POST("rentals")
    Call<RentalResponse> createRental(@Body Rental rental);

    @GET("rentals")
    Call<RentalListResponse> getAllRentals();

    @DELETE("rentals/{id}")
    Call<Void> deleteRental(@Path("id") int id);
}
```

### Making API Calls
```java
// Example: Get all caftans
ApiService apiService = ApiClient.getClient().create(ApiService.class);
Call<CaftanResponse> call = apiService.getAllCaftans();

call.enqueue(new Callback<CaftanResponse>() {
    @Override
    public void onResponse(Call<CaftanResponse> call, Response<CaftanResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            List<Caftan> caftans = response.body().getData();
            // Update UI
        }
    }

    @Override
    public void onFailure(Call<CaftanResponse> call, Throwable t) {
        // Handle error
        Log.e(TAG, "Error: " + t.getMessage());
    }
});
```

## 🐛 Troubleshooting

### Common Issues

#### 1. Network Error / Cannot Connect to API
**Symptoms**: "Failed to connect" or "Network error" messages

**Solutions**:
- ✅ Ensure backend server is running (`php artisan serve`)
- ✅ Check `ApiClient.java` has correct BASE_URL
- ✅ For emulator: Use `10.0.2.2` instead of `localhost`
- ✅ For real device: Use your computer's IP address
- ✅ Verify `AndroidManifest.xml` has `INTERNET` permission
- ✅ Check firewall is not blocking port 8000

#### 2. Images Not Loading
**Symptoms**: Placeholder images or blank image views

**Solutions**:
- ✅ Check internet connection
- ✅ Verify Glide dependency in `build.gradle.kts`
- ✅ Check image URLs from API are valid
- ✅ Add `usesCleartextTraffic="true"` in `AndroidManifest.xml` for HTTP

#### 3. Build Errors
**Symptoms**: Gradle build fails

**Solutions**:
- ✅ Clean project: `Build → Clean Project`
- ✅ Rebuild: `Build → Rebuild Project`
- ✅ Invalidate caches: `File → Invalidate Caches / Restart`
- ✅ Check Gradle version compatibility
- ✅ Sync Gradle files: `File → Sync Project with Gradle Files`

#### 4. Date Picker Not Working
**Symptoms**: Calendar doesn't open or dates don't save

**Solutions**:
- ✅ Check `RentActivity.java` has proper DatePickerDialog setup
- ✅ Verify EditText fields have `android:focusable="false"`
- ✅ Ensure click listeners are set on date fields

#### 5. RecyclerView Not Showing Data
**Symptoms**: Empty screen even with data from API

**Solutions**:
- ✅ Check adapter is set on RecyclerView
- ✅ Verify `notifyDataSetChanged()` is called after data update
- ✅ Ensure RecyclerView has `layout_height` and `layout_width`
- ✅ Check if data list is actually populated (use Log.d)

## 🔒 Permissions

Required permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 🚀 Building APK

### Debug APK (for testing)
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK (for distribution)
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release-unsigned.apk`

### Using Batch File
```cmd
BUILD_APP.bat
```

### Install APK on Device
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📊 Performance Tips

1. **Image Loading**: Glide automatically caches images for fast loading
2. **RecyclerView**: Uses ViewHolder pattern for efficient scrolling
3. **API Calls**: Avoid making multiple calls simultaneously
4. **Memory**: Clear image cache periodically if needed
5. **Network**: Implement retry logic for failed requests

## 🧪 Testing

### Manual Testing Checklist
- [ ] Welcome screen displays and animates correctly
- [ ] Caftan list loads and shows all items
- [ ] Availability badges show correct status
- [ ] Filter by size works
- [ ] Sort by price works
- [ ] Caftan details display correctly
- [ ] Date pickers open and set dates
- [ ] Rental submission works
- [ ] My Rentals screen shows rentals
- [ ] Delete rental works with confirmation
- [ ] Navigation works (back buttons)
- [ ] Error messages display properly

### Test User Flow
1. Open app → See welcome screen → Click "Get Started"
2. Browse caftans → Click on a caftan
3. View details → Click "Rent Now"
4. Fill rental form → Select dates → Submit
5. Navigate to "My Rentals" from menu
6. View rental → Delete rental → Confirm

## 📱 Supported Devices
- **Minimum**: Android 7.0 (API 24)
- **Recommended**: Android 9.0+ (API 28+)
- **Screen Sizes**: Phone and tablet
- **Orientations**: Portrait (primary), Landscape (supported)

## 🔄 Version History

### Version 1.0 (Current)
- ✅ Welcome screen with animations
- ✅ Browse caftans with availability status
- ✅ Filter and sort functionality
- ✅ Detailed caftan view
- ✅ Rental form with date pickers
- ✅ My Rentals management
- ✅ Delete rentals with confirmation
- ✅ Material Design 3 UI
- ✅ Pull-to-refresh
- ✅ Error handling

## 📚 Resources

- [Android Developers](https://developer.android.com/)
- [Material Design](https://material.io/design)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Glide Documentation](https://github.com/bumptech/glide)

## 📄 License
Educational project for Mobile Programming course.

---

**Built with Java & Material Design** ❤️
