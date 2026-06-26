# DailyPulseCMP

Cross-platform news reader built with **Compose Multiplatform (CMP)**, sharing both business logic and UI across Android, iOS, and Desktop with a single Kotlin codebase.

## Features

- Browse and read news articles from multiple sources
- Manage preferred news sources
- Single shared Compose UI running natively on all platforms

## Tech Stack

- **Compose Multiplatform** (CMP)
- **Kotlin Multiplatform** — shared logic and UI
- **SQLDelight** — multiplatform local database
- **Ktor** — multiplatform HTTP client
- **Koin** — multiplatform dependency injection
- Targets: **Android**, **iOS**, **Desktop (JVM)**

## Structure

```
├── androidApp/   # Android entry point
├── iosApp/       # iOS entry point (Kotlin/Native)
├── desktop/      # Desktop (JVM) entry point
└── shared/       # Shared Compose UI + business logic
```

## Getting Started

- **Android**: open in Android Studio and run `androidApp`
- **Desktop**: `./gradlew :desktop:run`
- **iOS**: open `iosApp/iosApp.xcodeproj` in Xcode

