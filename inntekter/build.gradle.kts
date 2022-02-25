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
        kotlinOptions.jvmTarget = "17"
        source(generateAvro)
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

sourceSets["main"].java.srcDirs("$buildDir/generated/avro")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/aap-avro")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("Avro Skjema Inntekter")
                artifactId = "inntekter"
                description.set("Avro skjema for aap inntekter")
                url.set("https://github.com/navikt/aap-avro")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/navikt/aap-avro.git")
                    developerConnection.set("scm:git:ssh://github.com/navikt/aap-avro.git")
                    url.set("https://github.com/navikt/aap-avro")
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
