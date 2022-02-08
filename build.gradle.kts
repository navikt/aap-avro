import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask

plugins {
    `java-library`
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
}

repositories {
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")
    mavenCentral()
}

tasks {
    val generateProtocol = task("generateProtocol", GenerateAvroProtocolTask::class) {
        source("main/no/nav/aap/avro")
        setOutputDir(file("$buildDir/generated/avpr"))
    }

    val generateSchema = task("generateSchema", GenerateAvroSchemaTask::class) {
        dependsOn(generateProtocol)
        source("$buildDir/generated/avpr")
        setOutputDir(file("$buildDir/generated/avsc"))
    }

    val generateAvro = task("generateAvro", GenerateAvroJavaTask::class) {
        dependsOn(generateSchema)
        source("$buildDir/generated/avsc")
        setOutputDir(file("$buildDir/generated/avro"))
    }

    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        source(generateAvro)
    }
}

dependencies {
    api("org.apache.avro:avro:1.11.0")
}

java.sourceSets["main"].java.srcDirs("main")
sourceSets["main"].java.srcDirs("build/generated/avro")
