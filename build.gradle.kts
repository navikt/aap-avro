import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
    `maven-publish`
    `java-library`
}

allprojects {
    repositories {
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

subprojects {
    group = "com.github.navikt"
    version = "1.0.0-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.davidmc24.gradle.plugin.avro")
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")

    tasks {
        val generateProtocol = task<GenerateAvroProtocolTask>("generateProtocol") {
            source("main")
            setOutputDir(file("$buildDir/generated/avpr"))
        }

        val generateSchema = task<GenerateAvroSchemaTask>("generateSchema") {
            dependsOn(generateProtocol)
            source("$buildDir/generated/avpr")
            setOutputDir(file("$buildDir/generated/avsc"))
        }

        val generateAvro = task<GenerateAvroJavaTask>("generateAvro") {
            dependsOn(generateSchema)
            source("$buildDir/generated/avsc")
            setOutputDir(file("$buildDir/generated/avro"))
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "18"
            source(generateAvro)
        }

        withType<Jar> {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = project.name
                from(components["java"])
            }
        }
    }

    sourceSets["main"].java.srcDirs("$buildDir/generated/avro")
    kotlin.sourceSets["test"].kotlin.srcDirs("test")
}