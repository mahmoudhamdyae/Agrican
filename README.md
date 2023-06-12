# Agrican

# Project Structure

| Name  | Description |
| ------------- | ------------- |
| build.gradle  | defines your build configuration that applies to all modules  |
| app/build.gradle  | Defines the module-specific build configurations  |
| app/src/androidTest  | Contains code for instrumentation tests that run on an Android device  |
| app/src/test  | Contains code for local tests that run on your host JVM  |
| app/src/main/AndroidManifest.xml  | Describes the nature of the application and each of its components  |
| app/src/main/res  | Contains application resources, such as drawable files, layout files, and UI string files  |
| app/src/main/java  | Contains Kotlin or Java code sources, or both  |
| app/src/main/java/com/example/agrican/ui  | The UI layer that displays application data on the screen  |
| app/src/main/java/com/example/agrican/data  | The data layer that contains the business logic of your app and exposes application data  |
| app/src/main/java/com/example/agrican/domain  | An additional layer to simplify and reuse the interactions between the UI and data layers  |
