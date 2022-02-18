plugins {
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0" apply false
    kotlin("jvm") version "1.6.10" apply false
    `java-library` apply true
}

subprojects {
    repositories {
        mavenCentral()
    }
}
