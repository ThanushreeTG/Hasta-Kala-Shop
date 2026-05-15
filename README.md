# Hasta Kala Shop

Hasta Kala Shop is an Android application developed for managing and analyzing handmade product sales and inventory. The app helps shop owners maintain inventory records, track sales history, and visualize analytics using interactive charts.

## Features

* Add and manage handmade product inventory
* Record product sales
* View complete sales history
* Analytics dashboard with charts
* Modern Jetpack Compose UI
* Local database storage using Room Database
* Navigation between multiple screens
* Material Design UI components

## Tech Stack

### Frontend

* Kotlin
* Jetpack Compose
* Material 3
* Navigation Compose

### Backend / Database

* Room Database
* SQLite (through Room ORM)

### Architecture

* MVVM Architecture
* ViewModel
* DAO Pattern
* State Management using Compose

### Charts & Analytics

* MPAndroidChart Library

## Project Structure

```
Hasta_kala/
│
├── app/
│   ├── src/main/java/com/hastakala/shop/
│   │   ├── data/
│   │   ├── navigation/
│   │   ├── screens/
│   │   ├── ui/
│   │   └── MainActivity.kt
│   │
│   └── build.gradle.kts
│
└── settings.gradle.kts
```

## Main Modules

### Home Screen

Displays inventory details and overview of products.

### Add Sale Screen

Allows users to add and manage sales records.

### History Screen

Shows previous sales and inventory history.

### Analytics Screen

Displays graphical analysis using pie charts and bar charts.

## Dependencies Used

```gradle
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose")
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
```

## Requirements

* Android Studio Hedgehog or later
* JDK 17
* Minimum SDK: 26
* Target SDK: 35

## Installation Steps

1. Clone the repository:

```bash
git clone https://github.com/your-username/hasta-kala-shop.git
```

2. Open the project in Android Studio.

3. Sync Gradle dependencies.

4. Run the application on:

    * Android Emulator
    * Physical Android Device

## Build Configuration

* Compile SDK: 35
* Min SDK: 26
* Kotlin JVM Target: 17

## Screens Included

* Home Screen
* Add Sale Screen
* History Screen
* Analytics Screen

## Future Enhancements

* Firebase Authentication
* Online Product Ordering
* Payment Gateway Integration
* Cloud Database Support
* Admin Dashboard
* Multi-user Access
* Product Image Upload

## Author

**Thanushree TG**

## License

This project is developed for educational and learning purposes.
