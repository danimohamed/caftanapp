# 👗 Caftan Rental Application
## 🎥 Demo Video

To see the application in action, check out the demo video below:

<video width="640" height="360" controls>
  <source src="https://www.dropbox.com/scl/fi/3t2yhq6ubjeprw8xwi8fg/Screen_recording_20260106_112331.webm?rlkey=jxt0wt95804xfb0katy7v0dw5&st=arqudbkp&dl=0" type="video/webm">
  Your browser does not support the video tag.
</video>

A complete mobile application for renting traditional Moroccan caftans, built with Android (Java) frontend and Laravel (PHP) backend.

## 📱 Project Overview

**Caftan Rental App** is a full-stack mobile application that allows users to:
- Browse beautiful traditional Moroccan caftans
- View detailed information about each caftan
- Check real-time availability (In Stock / Out of Stock)
- Rent caftans by selecting dates through an intuitive calendar picker
- Manage their rentals (view and delete)
- Filter caftans by size and sort by price

## 🏗️ Architecture

```
caftanapp/
├── app/                    # Android Application (Frontend)
│   ├── src/main/java/      # Java source code
│   ├── src/main/res/       # Resources (layouts, drawables, values)
│   └── build.gradle.kts    # App dependencies
│
├── backend/                # Laravel API (Backend)
│   ├── app/                # Application logic (Models, Controllers)
│   ├── database/           # Migrations and seeders
│   ├── routes/             # API routes
│   └── public/             # Public assets and entry point
│
├── BUILD_APP.bat           # Quick build script
└── README.md               # This file
```

## 🛠️ Technology Stack

### Frontend (Android)
- **Language**: Java
- **Min SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 36)
- **Architecture**: MVC Pattern
- **Libraries**:
  - Retrofit 2.9.0 - REST API communication
  - Gson 2.10.1 - JSON parsing
  - Glide 4.16.0 - Image loading and caching
  - Material Design Components - Modern UI
  - RecyclerView - Efficient list display

### Backend (Laravel)
- **Framework**: Laravel 10.x
- **Language**: PHP 8.1+
- **Database**: MySQL 8.0
- **API Style**: RESTful JSON API

## 🚀 Quick Start

### Prerequisites
- **Android Studio** (latest version)
- **PHP 8.1+**
- **Composer**
- **MySQL 8.0**
- **JDK 11+**

### Backend Setup

1. **Navigate to backend directory**
   ```bash
   cd backend
   ```

2. **Install dependencies**
   ```bash
   composer install
   ```

3. **Configure environment**
   - Copy `.env.example` to `.env`
   - Update database credentials in `.env`:
     ```
     DB_DATABASE=caftan_rental
     DB_USERNAME=root
     DB_PASSWORD=your_password
     ```

4. **Create database**
   ```sql
   CREATE DATABASE caftan_rental;
   ```

5. **Run migrations and seeders**
   ```bash
   php artisan key:generate
   php artisan migrate
   php artisan db:seed
   ```

6. **Start the server**
   ```bash
   php artisan serve
   ```
   Server runs at: `http://127.0.0.1:8000`

### Frontend Setup

1. **Open project in Android Studio**
   - Open the root folder `caftanapp`
   - Wait for Gradle sync to complete

2. **Configure API endpoint** (if needed)
   - Edit `app/src/main/java/com/example/frontend/api/ApiClient.java`
   - Update `BASE_URL`:
     ```java
     // For Android Emulator
     private static final String BASE_URL = "http://10.0.2.2:8000/api/";
     
     // For Real Device (use your computer's IP)
     private static final String BASE_URL = "http://192.168.1.XXX:8000/api/";
     ```

3. **Build and Run**
   - Click "Run" in Android Studio, OR
   - Use the batch file: `BUILD_APP.bat`

## ✨ Features

### 1. Welcome Screen
- Professional splash screen with branding
- Smooth animations
- "Get Started" button to enter the app

### 2. Browse Caftans (Main Screen)
- Grid/List view of all caftans
- Each card shows:
  - Caftan image
  - Name and size
  - Price (MAD)
  - Availability badge (Available/Rented)
- **Filter by Size**: All, S, M, L, XL
- **Sort by Price**: Low to High, High to Low
- Pull-to-refresh to reload data

### 3. Caftan Details
- Large image display
- Complete information:
  - Name, Size, Price
  - Stock status (In Stock/Out of Stock)
- "Rent Now" button (disabled if out of stock)
- Back navigation

### 4. Rent Caftan
- Customer information form
- **Date Picker Calendars**:
  - Start date picker
  - End date picker
  - Prevents selecting past dates
  - Auto-formatted dates (YYYY-MM-DD)
- Form validation
- Submit rental request

### 5. My Rentals
- View all your rentals
- Shows:
  - Caftan name and image
  - Rental dates
  - Customer name and phone
- **Delete rentals** with confirmation dialog
- Empty state when no rentals

### 6. Navigation
- **Top Toolbar**: App title and menu
- **Menu Options**:
  - My Rentals
  - Refresh Data
- **Back Navigation**: Proper parent activity handling

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/test` | Test API connection |
| GET | `/api/caftans` | Get all caftans |
| GET | `/api/caftans/{id}` | Get single caftan |
| POST | `/api/rentals` | Create a rental |
| GET | `/api/rentals` | Get all rentals |
| DELETE | `/api/rentals/{id}` | Delete a rental |

For complete API documentation, see [backend/API_DOCUMENTATION.md](backend/API_DOCUMENTATION.md)

## 📁 Project Structure

### Frontend Structure
```
app/src/main/java/com/example/frontend/
├── activities/
│   ├── WelcomeActivity.java      # Splash screen
│   ├── MainActivity.java         # Caftan list
│   ├── DetailsActivity.java      # Caftan details
│   ├── RentActivity.java         # Rental form
│   └── MyRentalsActivity.java    # View rentals
├── adapters/
│   ├── CaftanAdapter.java        # Caftan RecyclerView adapter
│   └── RentalAdapter.java        # Rental RecyclerView adapter
├── api/
│   ├── ApiClient.java            # Retrofit client
│   └── ApiService.java           # API endpoints
└── models/
    ├── Caftan.java               # Caftan model
    ├── Rental.java               # Rental model
    └── Response models           # API response models
```

### Backend Structure
```
backend/
├── app/
│   ├── Http/Controllers/
│   │   ├── CaftanController.php  # Caftan endpoints
│   │   └── RentalController.php  # Rental endpoints
│   └── Models/
│       ├── Caftan.php            # Caftan model
│       └── Rental.php            # Rental model
├── database/
│   ├── migrations/               # Database schema
│   └── seeders/
│       └── CaftanSeeder.php      # Sample data (10 caftans)
└── routes/
    └── api.php                   # API routes
```

## 🎨 UI/UX Features

- **Material Design 3** components
- **Responsive layouts** for different screen sizes
- **Status bar integration** - Proper toolbar alignment
- **Loading states** - Progress indicators during API calls
- **Error handling** - User-friendly error messages
- **Image caching** - Fast image loading with Glide
- **Color-coded status**:
  - Green badges for "Available"
  - Red badges for "Rented"
- **Smooth animations** - Professional transitions

## 🔧 Build & Deployment

### Build APK
```bash
# Clean build
gradlew clean

# Build debug APK
gradlew assembleDebug

# Build release APK
gradlew assembleRelease
```

Or use the quick build script:
```cmd
BUILD_APP.bat
```

APK location: `app/build/outputs/apk/debug/app-debug.apk`

## 🐛 Troubleshooting

### Backend Issues

**Issue**: API returns 500 error
- Check `.env` database configuration
- Verify database exists and migrations ran
- Check Laravel logs: `storage/logs/laravel.log`

**Issue**: CORS errors
- Verify `config/cors.php` allows your domain
- Laravel handles CORS by default for API routes

### Frontend Issues

**Issue**: Network error / Cannot connect to API
- For emulator, use `http://10.0.2.2:8000/api/`
- For real device, use your computer's IP
- Ensure backend server is running
- Check `AndroidManifest.xml` has internet permission

**Issue**: Images not loading
- Check internet connection
- Verify Glide dependencies
- Images are from Unsplash (external URLs)

**Issue**: Build errors
- Clean project: `Build → Clean Project`
- Rebuild: `Build → Rebuild Project`
- Invalidate caches: `File → Invalidate Caches / Restart`

## 📝 Sample Data

The backend includes a seeder with 10 traditional Moroccan caftans:
1. Caftan Royal Fassi - 500 MAD
2. Caftan Marocain Brodé Or - 650 MAD
3. Caftan Takchita Luxe - 800 MAD
4. Caftan Traditionnel Fès - 450 MAD
5. Caftan Mariée Moderne - 950 MAD
6. Caftan Velours Royal - 750 MAD
7. Caftan Soirée Élégant - 550 MAD
8. Caftan Traditionnel Blanc - 600 MAD
9. Caftan Moderne Chic - 700 MAD
10. Caftan Haute Couture - 1200 MAD

## 🧪 Testing

### Test API Connection
```bash
# From terminal
curl http://127.0.0.1:8000/api/test

# Or visit in browser
http://127.0.0.1:8000/api/test
```

Expected response:
```json
{
  "success": true,
  "message": "API de location de caftans marocains - Fonctionne correctement!",
  "version": "1.0.0"
}
```

## 🎥 Demo Video

To see the application in action, check out the demo video below:

<video width="640" height="360" controls>
  <source src="https://www.dropbox.com/scl/fi/3t2yhq6ubjeprw8xwi8fg/Screen_recording_20260106_112331.webm?rlkey=jxt0wt95804xfb0katy7v0dw5&st=arqudbkp&dl=0" type="video/webm">
  Your browser does not support the video tag.
</video>

## 📄 License

This is a student project developed for educational purposes.

## 👥 Contributing

This is an academic project. Contributions are welcome for educational improvements.

## 📧 Support

For issues or questions, please check:
- [Backend README](backend/README.md)
- [API Documentation](backend/API_DOCUMENTATION.md)
- Android Studio documentation
- Laravel documentation

## 🔄 Version

- **App Version**: 1.0
- **API Version**: 1.0.0
- **Last Updated**: January 2026

---

**Developed with ❤️ for Mobile Programming Course**
