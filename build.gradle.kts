import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask

plugins {
    `java-library`
    `maven-publish`
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
}

dependencies {
    api("org.apache.avro:avro:1.11.0")
}

tasks {
    val generateProtocol = task("generateProtocol", GenerateAvroProtocolTask::class) {
        source("main")
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

java.sourceSets["main"].java.srcDirs("main")
sourceSets["main"].java.srcDirs("build/generated/avro")

group = "no.nav.aap"
version = "${project.version}"

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/aap-avroskjema")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("avroskjema")
                artifactId = "avroskjema"
                description.set("Avro skjema klasser for AAP")
                url.set("https://github.com/navikt/aap-avroskjema")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/navikt/aap-avroskjema.git")
                    developerConnection.set("scm:git:https://github.com/navikt/aap-avroskjema.git")
                    url.set("https://github.com/navikt/aap-avroskjema")
                }
            }
            from(components["java"])
        }
    }
}
