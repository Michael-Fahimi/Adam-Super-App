# Adam Super App

## 📱 Overview

**Adam Super App** is a modern Android super-application that consolidates multiple services into a single unified platform. It combines popular services like Uber, Hotel Booking, Pet Care, Delivery, Banking, and Tinder into one seamless application.

This is a full-featured Android application demonstrating advanced architectural patterns, modern development practices, and professional-grade features.

## ✨ Features

### Integrated Services

1. **🚗 Uber** - Ride-sharing and transportation services
2. **🏨 Hotel** - Hotel booking and reservation system with favorites functionality
3. **🐾 Pet Care** - Pet care appointment booking and pet guardian search
4. **🍕 Delivery** - Food and parcel delivery services with map-based tracking
5. **🏦 iBank** - Banking services and financial transactions
6. **❤️ Tinder** - Social networking and matching services

### Core Functionality

- **Firebase Integration**
  - Firebase Authentication with Google Sign-In
  - Firebase Analytics for user behavior tracking
  - Firebase Crashlytics for crash reporting and monitoring
  
- **Location Services**
  - Google Maps integration
  - Real-time location tracking
  - Location-based service recommendations

- **User Authentication**
  - Google Sign-In integration
  - Sign-up and Sign-in screens
  - Secure session management

- **Modern UI**
  - Jetpack Compose for modern UI development
  - Material Design 3
  - Responsive and adaptive layouts

## 🏗️ Architecture

### Project Structure

```
PainfulNeedleSuperApp-dev/
├── app/                          # Main application module
│   ├── src/main/
│   │   ├── java/.../
│   │   │   ├── auth/            # Authentication logic
│   │   │   ├── di/              # Dependency Injection setup
│   │   │   ├── navigation/      # App-wide navigation
│   │   │   └── ui/              # Shared screens
│   │   └── res/                 # Resources (colors, strings, etc.)
│   └── build.gradle.kts
│
├── core/                         # Shared utilities and components
│   ├── ui/
│   │   ├── components/          # Reusable UI components
│   │   ├── theme/               # Theme, colors, typography
│   │   └── screen/              # Shared screens
│   └── build.gradle.kts
│
├── feature-uber/                # Uber feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── feature-hotel/               # Hotel feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── feature-pet/                 # Pet care feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── feature-delivery/            # Delivery feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── feature-bank/                # Banking feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── feature-tinder/              # Tinder feature module
│   ├── src/main/java/...
│   ├── src/main/res/
│   └── build.gradle.kts
│
├── gradle/                       # Gradle configuration
│   └── libs.versions.toml        # Centralized version management
│
├── build.gradle.kts             # Root build file
├── settings.gradle.kts          # Project structure definition
└── gradle.properties            # Gradle properties
```

### Architectural Patterns

#### 1. **Modular Architecture**
The application uses a feature-based modular architecture where each service is isolated in its own feature module:
- Each feature module is independently buildable
- Modules communicate through the main `app` module
- Clear separation of concerns and responsibilities

#### 2. **Dependency Injection (Hilt)**
- Uses Google Dagger Hilt for dependency injection
- Provides compile-time safety and performance
- Centralized DI configuration in `AppModule`

#### 3. **Navigation Architecture**
- Jetpack Compose Navigation for screen navigation
- Modular navigation graphs for each feature
- Central `AppNavigation` orchestrates inter-feature navigation
- Nested navigation controllers for feature isolation

#### 4. **Firebase Integration**
- Centralized Firebase setup through `PineAndroidApp` (Application class)
- Real-time authentication state management
- Automatic crash reporting and analytics

## 🛠️ Technology Stack

### Build & Configuration
- **Gradle** 8.8.2 with Kotlin DSL (build.gradle.kts)
- **Kotlin** 2.1.0
- **Android Gradle Plugin (AGP)** 8.8.2
- **Target API Level**: 35 (Android 15)
- **Minimum API Level**: 25 (Android 7.0)

### Android Framework
- **Jetpack Compose** 2025.03.00
  - Modern UI framework
  - Declarative UI composition
  - Material Design 3 components

- **AndroidX Libraries**
  - Core KTX 1.15.0
  - Lifecycle Runtime 2.8.7
  - Navigation Compose 2.8.9
  - Activity Compose 1.10.1

### Firebase Suite
- **Firebase BOM** 33.10.0
  - Firebase Analytics
  - Firebase Authentication 23.2.0
  - Firebase Crashlytics 3.0.3

### Dependency Injection
- **Dagger Hilt** 2.51.1
  - hilt-android
  - hilt-android-compiler
  - hilt-navigation-compose 1.2.0

### Location & Maps
- **Google Play Services**
  - Maps Compose 4.4.0
  - Play Services Maps 19.1.0
  - Play Services Location 21.3.0

### Authentication
- **Google ID** 1.1.1 (for Google Sign-In)
- **Firebase Auth KTX** 23.2.0

### Testing
- **JUnit** 4.13.2
- **AndroidX Test**
  - JUnit 1.2.1
  - Espresso Core 3.6.1
  - Compose UI Test JUnit4

## 📋 Feature Module Details

### Feature: Uber
**Location**: `feature-uber/`

**Functionality**:
- Ride selection and booking
- Driver matching
- Trip tracking with maps
- Nested navigation for ride lifecycle

**Key Files**:
- Navigation graph setup for ride services
- Integration with location services

### Feature: Hotel
**Location**: `feature-hotel/`

**Functionality**:
- Hotel search and discovery
- Booking management
- Favorites/Wishlist functionality
- Map-based hotel location viewing

**Key Files**:
- `FavoriteViewModel` - Manages user favorites
- Hotel search and booking flows

### Feature: Pet Care
**Location**: `feature-pet/`

**Functionality**:
- Pet profile creation with photo capture
- Pet guardian/caregiver search
- Appointment scheduling with date selection
- Pet care request ordering

**Key Screens**:
- Start Screen - Introduction
- Your Dog Details - Pet information
- Pet Care Person List - Guardian search
- Choose Date Screen - Appointment scheduling
- Order Screen - Finalize appointment

### Feature: Delivery
**Location**: `feature-delivery/`

**Functionality**:
- Order placement and tracking
- Delivery address management
- Real-time delivery tracking with maps
- Parcel information display
- Delivery personnel details

**Key Screens**:
- Delivery Home Screen
- Address input and management
- Map-based parcel/order view
- Delivery status tracking

### Feature: iBank (Banking)
**Location**: `feature-bank/`

**Functionality**:
- Banking service overview
- Account services menu
- Transaction history
- Credit/Financial products

**Key Screens**:
- iBank Home Screen
- Services navigation
- Transaction display
- Credit card services

### Feature: Tinder
**Location**: `feature-tinder/`

**Functionality**:
- Social networking and matching
- User card swiping
- Match management
- Social connection features

## 🔐 Authentication & Security

### Firebase Authentication Flow
1. User launches app
2. Check current Firebase user status
3. If not authenticated → Show Sign-In/Sign-Up screens
4. On successful authentication → Show Home screen
5. Home screen displays all available services

### Sign-In Methods
- **Google Sign-In** - Primary authentication method
- Email/Password authentication with custom forms

## 📍 Navigation Flow

```
App Launch
    ↓
[Is User Logged In?]
    ├─ Yes → Home Screen (Service Grid)
    │         ├─ Hotel Service
    │         ├─ Uber Service
    │         ├─ Tinder Service
    │         ├─ iBank Service
    │         ├─ Delivery Service
    │         └─ Pet Care Service
    │
    └─ No → Sign-In Screen
              ├─ Sign In
              └─ Navigate to Sign-Up
              
Each Service has its own navigation graph and flow
```

## 🎨 UI/UX Highlights

- **Material Design 3**: Modern design system with dynamic theming
- **Jetpack Compose**: Reactive UI with less boilerplate
- **Reusable Components**: Shared components in `core` module
- **Responsive Layouts**: Adapt to different screen sizes
- **Custom Themes**: Feature-specific and app-wide theming

## 🚀 Getting Started

### Prerequisites
- Android Studio (Latest version)
- JDK 11+
- Android SDK API 35
- Google Play Services configured

### Build & Run

1. **Clone and setup**:
   ```bash
   git clone <repository>
   cd PainfulNeedleSuperApp-dev
   ```

2. **Configure Firebase**:
   - Place `google-services.json` in `app/` directory
   - Ensure Firebase project is properly configured

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run the app**:
   ```bash
   ./gradlew installDebug
   ```

### Build Variants
- **Debug Build**: Development with all logging and debugging tools
- **Release Build**: Optimized build with ProGuard minification

## 📊 Dependency Management

The project uses a centralized version management approach via `gradle/libs.versions.toml`:

- All dependency versions defined in one place
- Plugins configured in same file
- Easy to update and maintain
- Prevents version conflicts

**Key Dependencies**:
- Firebase (Analytics, Auth, Crashlytics)
- Jetpack Compose (UI framework)
- Dagger Hilt (Dependency Injection)
- Google Maps (Location services)
- androidx libraries (Core components)

## 🔍 Code Quality & Best Practices

- **Kotlin 2.1.0**: Latest Kotlin features and optimizations
- **Type Safety**: Leverages Kotlin's strong type system
- **Coroutines Ready**: Lifecycle-aware and coroutine-friendly code
- **ProGuard Minification**: Code obfuscation for production
- **Testing Framework**: JUnit and Espresso for testing

## 📦 Build Configuration

### Android Configuration
- **Compile SDK**: 35
- **Target SDK**: 35
- **Min SDK**: 25
- **Java/Kotlin Compatibility**: JVM 11
- **Build Tools**: Gradle 8.8.2

### Gradle Features
- KSP (Kotlin Symbol Processing) for annotations
- Google Services plugin for Firebase
- Google Dagger Hilt plugin for DI

## 🎯 Development Highlights

### Strengths
1. **Modular Design**: Each service is independently manageable
2. **Modern Tech Stack**: Latest Android and Jetpack libraries
3. **Firebase Integration**: Production-ready analytics and error tracking
4. **Scalability**: Easy to add new features or services
5. **Code Organization**: Clear separation of concerns
6. **Type Safety**: Leverages Kotlin's type system
7. **Testability**: DI-friendly architecture

### Architectural Decisions
- **Feature Modules**: Enable independent development and testing
- **Hilt DI**: Reduces boilerplate and improves testability
- **Compose Navigation**: Modern, declarative navigation
- **Centralized Resources**: Core module for shared UI components

## 🔄 Feature Integration Pattern

Each feature module follows this pattern:

```kotlin
// Navigation Graph Definition
fun NavGraphBuilder.featureNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "feature_start",
        route = "feature_route"
    ) {
        composable("feature_start") { StartScreen(navController) }
        composable("feature_screen2") { Screen2(navController) }
        // ... more routes
    }
}

// Integration in App Navigation
NavHost(navController, startDestination = ...) {
    // ...
    featureNavGraph(navController)  // Add feature navigation
}
```

## 📝 Configuration Files

### Root Level
- `build.gradle.kts` - Root project build configuration
- `settings.gradle.kts` - Project module configuration
- `gradle.properties` - Global Gradle properties

### Gradle Wrapper
- `gradlew` / `gradlew.bat` - Gradle wrapper scripts
- `gradle/wrapper/gradle-wrapper.properties` - Wrapper configuration

### Firebase
- `app/google-services.json` - Firebase configuration (not in repo)

## 🎓 Learning Outcomes

This project demonstrates:
- Professional Android development practices
- Modern UI development with Jetpack Compose
- Modular architecture and scalability
- Firebase integration and real-world usage
- Dependency injection patterns
- Navigation in multi-feature apps
- Material Design 3 implementation
- Authentication and user management

## 📄 License

[Add your license information here]

## 👥 Author

**Developer**: Sina Fahimi ,Madhan Gangadari  
**Project Name**: Adam Super App  
**Package**: com.madhan.adamsuperapp

---

**Built with ❤️ using modern Android development practices**

