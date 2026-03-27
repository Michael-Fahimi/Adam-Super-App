# Adam Super App - Architecture & Design Document

## Table of Contents
1. [System Architecture](#system-architecture)
2. [Module Design](#module-design)
3. [Navigation Architecture](#navigation-architecture)
4. [Dependency Injection](#dependency-injection)
5. [Data Flow](#data-flow)
6. [Authentication Flow](#authentication-flow)
7. [Module Communication](#module-communication)
8. [Design Patterns](#design-patterns)

---

## System Architecture

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                     ADAM SUPER APP                          │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              APP MODULE (Main Application)           │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │ MainActivity (Compose-based entry point)       │  │  │
│  │  │ - Scaffold with Material 3                     │  │  │
│  │  │ - Hilt Dependency Injection                    │  │  │
│  │  └────────────────────────────────────────────────┘  │  │
│  │                                                        │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │ AppNavigation (Central Navigation Hub)        │  │  │
│  │  │ - Firebase Auth State Check                   │  │  │
│  │  │ - Route to Sign-In/Home/Features             │  │  │
│  │  │ - Integrates all feature nav graphs          │  │  │
│  │  └────────────────────────────────────────────────┘  │  │
│  │                                                        │  │
│  └──────────────────────────────────────────────────────┘  │
│                          │                                  │
│          ┌───────────────┼───────────────┐                 │
│          ▼               ▼               ▼                  │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐       │
│  │ Auth Module  │ │ Core Module  │ │ UI Theme     │       │
│  │              │ │              │ │              │       │
│  │ - SignIn     │ │ - Shared UI  │ │ - Material3  │       │
│  │ - SignUp     │ │ - Components │ │ - Colors     │       │
│  │ - Google     │ │ - Theme      │ │ - Typography │       │
│  │   Auth       │ │ - Utilities  │ │              │       │
│  └──────────────┘ └──────────────┘ └──────────────┘       │
│                                                              │
└─────────────────────────────────────────────────────────────┘
         │
         └───────────────────────────────┬─────────────────────────
                                         │
          ┌──────────────────────────────┼──────────────────────┐
          │                              │                      │
    ┌─────▼──────┐  ┌──────▼──────┐ ┌──▼────────┐  ┌──────▼───┐
    │Feature Uber │  │Feature Hotel │ │Pet Feature│  │Delivery  │
    │             │  │              │ │           │  │          │
    │ - Ride Book │  │- Search      │ │- Appt     │  │- Track   │
    │ - Tracking  │  │- Booking     │ │- Guardian │  │- Orders  │
    │ - Driver    │  │- Map View    │ │- Photos   │  │- Address │
    └─────┬──────┘  └──────┬──────┘ └──┬────────┘  └──────┬───┘
          │                │            │                  │
    ┌─────▼──────┐  ┌──────▼──────┐ ┌──▼────────┐  ┌──────▼───┐
    │Feature Bank │  │Feature Tinder│ │  (more...)│  │           │
    │             │  │              │ │           │  │           │
    │ - Services  │  │- Matching    │ │           │  │           │
    │ - Trans     │  │- Swipe       │ │           │  │           │
    │ - Accounts  │  │- Messages    │ │           │  │           │
    └─────┬──────┘  └──────┬──────┘ └──┬────────┘  │           │
          │                │            │          │           │
          └────────────────┼────────────┼──────────┴───────────┘
                           │            │
                    ┌──────▼────────────▼─────┐
                    │  Firebase Services      │
                    │  - Authentication       │
                    │  - Analytics           │
                    │  - Crashlytics         │
                    │  - Realtime Database   │
                    └─────────────────────────┘
                           │
                    ┌──────▼──────────────┐
                    │  Google Services    │
                    │  - Maps & Location  │
                    │  - Geolocation API  │
                    │  - Sign-In          │
                    └─────────────────────┘
```

### Architectural Layers

#### 1. **Presentation Layer (UI)**
- **Technology**: Jetpack Compose
- **Components**: Screens, Composables, ViewModels
- **Responsibility**: Rendering UI and handling user interactions
- **Location**: `feature-*/src/main/java/ui/screens`

#### 2. **Navigation Layer**
- **Technology**: Jetpack Compose Navigation
- **Components**: NavGraphs, NavControllers
- **Responsibility**: Routing between screens and features
- **Location**: `app/navigation`, `feature-*/navigation`

#### 3. **Dependency Injection Layer**
- **Technology**: Dagger Hilt
- **Components**: Modules, Provides, ViewModels
- **Responsibility**: Object creation and lifecycle management
- **Location**: `app/di`

#### 4. **Service & Integration Layer**
- **Technology**: Firebase SDK, Google Play Services
- **Components**: FirebaseAuth, Analytics, Crashlytics
- **Responsibility**: External service integration
- **Location**: `PineAndroidApp`, `AppModule`

#### 5. **Shared Resources Layer**
- **Technology**: Kotlin, Jetpack Compose
- **Components**: Reusable UI components, theme, utilities
- **Responsibility**: Shared functionality across modules
- **Location**: `core/src/main/java`

---

## Module Design

### Core Module Architecture

```
core/
├── src/main/java/com/madhan/core/
│   ├── ui/
│   │   ├── components/          # Reusable UI Components
│   │   │   └── PrimaryButton.kt
│   │   ├── repo/                # Shared repositories/utilities
│   │   │   └── MarkerPositions.kt
│   │   ├── screen/              # Shared screens
│   │   │   └── TakePhotoScreen.kt
│   │   └── theme/               # App theme and styling
│   │       ├── Color.kt
│   │       ├── Type.kt
│   │       └── Theme.kt
│   └── ...
├── build.gradle.kts             # Core module dependencies
├── consumer-rules.pro           # ProGuard rules
└── proguard-rules.pro
```

**Core Module Responsibilities**:
1. **Shared UI Components**: Buttons, text fields, dialogs
2. **Theme Management**: Colors, typography, styles
3. **Shared Utilities**: Common functions, helpers
4. **Photo Capture**: TakePhotoScreen for file operations

### Feature Module Architecture (Example: Pet)

```
feature-pet/
├── src/main/
│   ├── java/com/madhan/feature_pet/
│   │   ├── composable/           # Reusable composables within feature
│   │   │   ├── PetButton.kt
│   │   │   ├── PetTextField.kt
│   │   │   └── FilterSlider.kt
│   │   ├── data/                 # Data layer (models, repos)
│   │   │   └── DummyData.kt
│   │   ├── navigation/           # Feature navigation graph
│   │   │   └── PetNavHost.kt
│   │   └── screens/              # UI screens
│   │       ├── StartScreen.kt
│   │       ├── YourDogDetails.kt
│   │       ├── PetCarePersonListScreen.kt
│   │       ├── DogListScreen.kt
│   │       ├── ChooseDateScreen.kt
│   │       ├── OrderScreen.kt
│   │       ├── FilterScreen.kt
│   │       └── AppointmentSuccessfulScreen.kt
│   └── res/                      # Feature-specific resources
│       ├── drawable/
│       └── values/
├── AndroidManifest.xml
├── build.gradle.kts
└── proguard-rules.pro
```

**Feature Module Characteristics**:
- **Independence**: Can be compiled and tested separately
- **Encapsulation**: Internal navigation logic encapsulated
- **Resource Isolation**: Own drawable and value resources
- **Navigation Integration**: Exposes navigation graph via `NavGraphBuilder`
- **Core Dependency**: Depends on core for shared components

### Feature Module Types

#### 1. **Uber Feature Module**
```
Navigation Structure:
- uber_main (entry point)
- Nested navigation within feature
- Uses separate NavController
- Back navigation to home via callback
```

#### 2. **Hotel Feature Module**
```
Key Components:
- Hotel search and filtering
- Booking management
- Favorites system with ViewModel
- Map integration for locations
```

#### 3. **Pet Care Feature Module**
```
User Flow:
Start → Your Dog Details → Filter Guardians → 
Choose Date → Pet Care List → Order → Success
```

---

## Navigation Architecture

### App-Level Navigation Graph

```kotlin
NavHost(
    navController = navController,
    startDestination = if (isLoggedIn) "home" else "sign_in",
    route = "root"
) {
    // Auth routes
    composable("sign_in") { SignInScreen() }
    composable("sign_up") { SignUpScreen() }
    
    // Home route
    composable("home") { HomeScreen() }
    
    // Feature routes (nested graphs)
    navigation("uber") { SetupNavGraph() }
    deliveryNavGraph()
    petNavGraph()
    tinderNavGraph()
    bankNavGraph()
    hotelNavGraph()
}
```

### Feature Navigation Pattern

Each feature implements a navigation builder function:

```kotlin
fun NavGraphBuilder.featureNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "feature_start",
        route = "feature_route"
    ) {
        composable("feature_start") { 
            FeatureStartScreen(navController) 
        }
        composable("feature_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            FeatureDetailScreen(navController, id)
        }
        composable("feature_form") { 
            FeatureFormScreen(navController) 
        }
    }
}
```

### Navigation Key Points

1. **Lazy Loading**: Each feature's routes are only loaded when accessed
2. **Isolation**: Features have their own nav controllers and backstack
3. **Inter-feature Communication**: Through main app navigation
4. **Back Navigation**: Features can navigate back to home via callbacks
5. **State Management**: SavedStateHandle for sharing data between screens

---

## Dependency Injection

### Hilt Setup

#### 1. Application Class
```kotlin
@HiltAndroidApp
class PineAndroidApp : Application()
```

#### 2. Module Configuration
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Provide Firebase instances
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
    
    // Provide other dependencies
}
```

#### 3. ViewModel Injection
```kotlin
@HiltViewModel
class SigninWithGoogleViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    // Implementation
}
```

#### 4. Compose Integration
```kotlin
@Composable
fun MyScreen() {
    val viewModel: MyViewModel = hiltViewModel()
}
```

### DI Graph

```
AppModule
├── FirebaseAuth (Singleton)
├── Firebase Instance (Singleton)
├── Analytics (Singleton)
└── Crashlytics (Singleton)

ViewModels
├── SigninWithGoogleViewModel
├── FavoriteViewModel
└── Other Feature ViewModels
```

---

## Data Flow

### Authentication Data Flow

```
User Input (SignInScreen)
    ↓
SigninWithGoogleViewModel
    ↓
FirebaseAuth.signInWithCredential()
    ↓
[Auth State Change]
    ↓
AppNavigation detects user state
    ↓
Navigate to Home or redirect to Sign-In
```

### Feature Data Flow (Example: Pet Care)

```
User Action (Filter Selection)
    ↓
Screen State Update
    ↓
API/Database Query (DummyData for MVP)
    ↓
ViewModel update
    ↓
Composable recomposition
    ↓
Updated UI
```

### Home Screen Service Grid

```
HomeScreen loads
    ↓
Fetch services list (ServiceItem)
    ↓
Render grid with service tiles
    ↓
User clicks service
    ↓
Navigate to feature route
    ↓
Feature module's nav graph takes over
```

---

## Authentication Flow

### Firebase Authentication Integration

```
App Launch
    ↓
PineAndroidApp initializes (Hilt + Firebase)
    ↓
MainActivity.onCreate()
    ↓
AppNavigation checks Firebase.auth.currentUser
    ↓
[Is User Null?]
    ├─ Yes → startDestination = "sign_in"
    └─ No → startDestination = "home"
    ↓
Display appropriate screen
```

### Sign-In Flow

```
SignInScreen
    ↓
User enters credentials or chooses Google Sign-In
    ↓
SigninWithGoogleViewModel.signIn()
    ↓
FirebaseAuth.signInWithCredential()
    ↓
[Success?]
    ├─ Yes → Firebase state updates
    │         AppNavigation detects change
    │         Navigate to Home
    │
    └─ No → Show error toast
```

### Sign-Up Flow

```
SignUpScreen
    ↓
User creates credentials
    ↓
SigninWithGoogleViewModel.signUp()
    ↓
FirebaseAuth.createUserWithEmailAndPassword()
    ↓
[Success?]
    ├─ Yes → Navigate to Sign-In
    └─ No → Show error
```

### Logout Flow

```
HomeScreen (SignOut Button)
    ↓
SigninWithGoogleViewModel.logout()
    ↓
FirebaseAuth.signOut()
    ↓
State update → logoutState.SUCCESS
    ↓
Toast "Logout Successful"
    ↓
Navigate to Sign-In
    ↓
Clear backstack
```

---

## Module Communication

### Inter-Module Communication Patterns

#### 1. **Navigation-Based Communication**
Modules communicate through the main app's navigation:

```
Feature A User Action
    ↓
Feature A navigates to main app route
    ↓
Main app navigation routes to Feature B
    ↓
Feature B receives data via Bundle arguments
```

#### 2. **SharedStateHandle Communication**
Data sharing between screens:

```kotlin
// Screen 1 sets data
navController.previousBackStackEntry?.savedStateHandle?.set("key", value)

// Screen 2 retrieves data
val value = navController.currentBackStackEntry?.savedStateHandle?.get<Type>("key")
```

**Example from Pet Feature**:
```kotlin
// In ChooseDateScreen
navController.previousBackStackEntry?.savedStateHandle?.set("startDate", startDate)

// In PetCarePersonListScreen
val startDate = navController.currentBackStackEntry?.savedStateHandle?.get<String>("startDate")
```

#### 3. **ViewModel Scope Communication**
Shared ViewModels for cross-feature communication:

```kotlin
// Hotel feature - Favorites shared across app
val favoriteViewModel: FavoriteViewModel = viewModel()
hotelNavGraph(navController, favoriteViewModel)
```

#### 4. **Firebase Real-time Updates**
All features can subscribe to real-time database updates:

```
Feature A → Firebase Realtime DB
    ↓
Feature B listens to same path
    ↓
Both receive updates in real-time
```

### Service Registry (Home Screen)

The Home Screen maintains a list of available services:

```kotlin
val services = listOf(
    ServiceItem("Hotel", R.drawable.motel, Screen.Hotel.route),
    ServiceItem("Uber", R.drawable.uber, Screen.Uber.route),
    // ... more services
)
```

Each ServiceItem contains:
- Display name
- Icon resource
- Navigation route

---

## Design Patterns

### 1. **Model-View-ViewModel (MVVM)**

```
Model (Data) ← → ViewModel ← → View (Compose)
     ↓                ↓              ↓
 Firebase         StateFlow    UI State
 Local DB      LiveData      Events
 Repository    Coroutines
```

### 2. **Clean Architecture**

```
Presentation Layer  → Screens, Composables, ViewModels
                       ↓
Domain Layer       → Use Cases, Business Logic
                       ↓
Data Layer         → Repositories, API, Database
```

### 3. **Dependency Injection Pattern**

```
Dependencies provided by Hilt
    ↓
Constructor injection in ViewModels
    ↓
Service locator in Composables (hiltViewModel())
    ↓
Testable code without coupling
```

### 4. **Feature Module Pattern**

```
Each feature is:
- Self-contained
- Independently testable
- Reusable in different apps
- Has own navigation graph
- Depends on core module
```

### 5. **Navigation Graph Pattern**

```
App Navigation (Root)
    ├─ Auth Navigation (SignIn, SignUp)
    ├─ Home Navigation
    └─ Feature Navigation (Uber, Hotel, etc.)
           ↓
        Each feature has nested routes
        Feature controls internal navigation
        Main app controls feature transitions
```

### 6. **Repository Pattern**

```
ViewModel
    ↓
Repository (DummyData/Database)
    ↓
Data Source (Firebase, Local DB, API)
```

### 7. **Singleton Pattern**

```
Hilt provides single instances:
- FirebaseAuth
- Analytics
- Crashlytics
- Other services

Accessed throughout app via injection
```

### 8. **State Management**

```
UI State
    ↓
Flow/StateFlow (ViewModel)
    ↓
Composable collects via collectAsState()
    ↓
Recomposition on state change
```

---

## Build System & Configuration

### Gradle Structure

```
settings.gradle.kts (Module includes)
    ↓
build.gradle.kts (Root plugins)
    ↓
gradle/libs.versions.toml (Version catalog)
    ↓
feature-*/build.gradle.kts (Module config)
```

### Version Management

Centralized in `gradle/libs.versions.toml`:
- Kotlin version: 2.1.0
- Compose BOM: 2025.03.00
- Firebase BOM: 33.10.0
- Target API: 35
- Minimum API: 25

### Dependency Categories

```
Implementation Deps
├── AndroidX
├── Compose
├── Firebase
├── Dagger Hilt
├── Google Play Services
└── Custom Libraries

Test Deps
├── JUnit
├── Espresso
└── Compose Testing
```

---

## Performance Considerations

### 1. **Module Layering**
- Features only included when accessed
- Reduces initial app size
- Faster startup time

### 2. **Compose Optimization**
- Recomposition scope limited
- State hoisting reduces unnecessary recompositions
- Remember blocks cache computations

### 3. **Firebase Integration**
- Lazy initialization where possible
- Analytics batch reporting
- Crashlytics async reporting

### 4. **Image Loading**
- Drawable resources optimized
- Map tiles cached by Play Services
- Lazy loading of non-visible content

---

## Security Considerations

### 1. **Authentication**
- Firebase Authentication handles token management
- Google Sign-In uses OAuth 2.0
- Session tokens not stored in SharedPreferences

### 2. **Data Protection**
- Firebase Crashlytics excludes sensitive data
- ProGuard obfuscation in release builds
- HTTPS for all external APIs

### 3. **Code Obfuscation**
Release builds use ProGuard:
- `proguard-rules.pro` (App level)
- `consumer-rules.pro` (Feature level)

### 4. **Manifests & Permissions**
- Minimum required permissions declared
- Target API 35 (latest security practices)
- Tools namespace for build-time attributes

---

## Extension Points & Scalability

### Adding a New Feature

```
1. Create feature-newservice/ module
2. Implement navigation graph
3. Create screens and composables
4. Add dependencies in build.gradle.kts
5. Register in app/settings.gradle.kts
6. Add navigation route in AppNavigation
7. Add service to HomeScreen service list
```

### Adding Shared Components

```
1. Create component in core/ui/components/
2. Implement using Compose
3. Expose as public composable
4. Document in core module
5. Import in features: implementation(project(":core"))
```

### Adding New Services

```
Service Architecture:
- Feature module for UI
- Firebase backend for data
- ViewModels for state
- Navigation graph for routing
```

---

## Testing Strategy

### Unit Testing
- ViewModel testing with test ViewModels
- Utility function testing
- State management testing

### Integration Testing
- Navigation testing
- Feature integration
- Firebase mock integration

### UI Testing
- Compose preview testing
- Screen interaction testing
- Navigation flow testing

---

## Deployment & Release

### Build Process

```
Development Build:
./gradlew assembleDebug
    ↓
Debug APK with full logging

Release Build:
./gradlew assembleRelease
    ↓
ProGuard minification
    ↓
Code obfuscation
    ↓
Release APK
```

### Firebase Setup for Production

```
1. Configure Firebase project
2. Add google-services.json
3. Enable required services:
   - Authentication (Google Sign-In)
   - Analytics
   - Crashlytics
4. Create database rules
5. Deploy with signed APK
```

---

## Conclusion

This architecture provides:

✅ **Modularity**: Independent feature development  
✅ **Scalability**: Easy to add new features  
✅ **Maintainability**: Clear separation of concerns  
✅ **Testability**: DI-friendly design  
✅ **Performance**: Optimized Compose rendering  
✅ **Security**: Firebase + ProGuard protection  
✅ **Modern Stack**: Latest Android & Kotlin practices  

The design supports growth while maintaining code quality and developer experience.

