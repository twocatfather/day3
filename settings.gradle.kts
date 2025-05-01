rootProject.name = "day3"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        java
        id("org.springframework.boot") version "3.4.5"
        id("io.spring.dependency-management") version "1.1.7"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml")) // ✅ TOML 파일 경로 명시
        }
    }
}