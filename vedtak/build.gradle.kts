import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    kotlin("jvm")
    id("com.github.davidmc24.gradle.plugin.avro")
}

dependencies {
    api("org.apache.avro:avro:1.11.0")
    testImplementation(kotlin("test"))
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

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
        source(generateAvro)
    }
}

kotlin.sourceSets["main"].kotlin.srcDirs("main", "$buildDir/generated/avro")

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/${rootProject.name}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("vedtak")
                artifactId = "vedtak"
                description.set("Avro skjema for aap domene")
                url.set("https://github.com/navikt/${rootProject.name}")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/navikt/${rootProject.name}.git")
                    developerConnection.set("scm:git:ssh://github.com/navikt/${rootProject.name}.git")
                    url.set("https://github.com/navikt/${rootProject.name}")
                }
                developers {
                    developer {
                        organization.set("NAV (Arbeids- og velferdsdirektoratet) - The Norwegian Labour and Welfare Administration")
                        organizationUrl.set("https://www.nav.no")
                    }
                }
            }
            from(components["java"])
        }
    }
}
