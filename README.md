# KaizenApp

KaizenApp is an Android application to manage and display sports events, allowing users to mark events as favorites. The app utilizes modern Android development practices to ensure robust performance and easy maintenance.

## Technologies Used

- **Kotlin**: Programming language.
- **Room Database**: Local storage.
- **Coroutines**: Asynchronous tasks.
- **MVVM Architecture**: Separation of concerns.
- **JUnit**: Unit testing.
- **Espresso**: UI testing.
- **Dagger Hilt**: Dependency injection.
- **Clean Architecture**: Scalable and maintainable code.
- **LiveData**: Reactive UI updates.
- **Retrofit and OkHttp**: Network operations.

## Installation

1. Clone the repository:
   ```bash
   https://github.com/diegoamribeiro/KaizenApp.git
   ```
2. Open the project in Android Studio.
3. Sync with Gradle files.
4. Run the project on an emulator or physical device.

## Testing

### Unit Tests

Run unit tests with JUnit:

```bash
./gradlew test
```

### Instrumented Tests

Run UI tests with Espresso:

```bash
./gradlew connectedAndroidTest
```

## Architecture

Following MVVM and Clean Architecture principles:

- **Model**: Business logic and data.
- **View**: User interface.
- **ViewModel**: Mediator between Model and View.

### Clean Architecture Layers

- **Domain**: Use cases and business logic.
- **Data**: Data sources and repositories.
- **Presentation**: UI components and ViewModels.

## Contributions

Contributions are welcome! Open an issue or submit a pull request for improvements or bug fixes.

## License

This project is licensed under the MIT License - see the [[LICENSE](https://opensource.org/license/mit)] file for details.

## Acknowledgments

- Special thanks to the Android community for resources and tutorials.
- Inspired by principles of clean and maintainable code.

---

I hope this English version meets your requirements!
