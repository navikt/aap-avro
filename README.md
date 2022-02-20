# Avro-skjemaer for AAP

![img](https://img.shields.io/github/v/tag/navikt/aap-avro)

## Usage

### Medlem (medlemskap - LovMe)

```kotlin
implementation("no.nav.aap.avro:medlem:1.1.3")
```

### Vedtak (aap - domene)

```kotlin
implementation("no.nav.aap.avro:vedtak:1.1.5")
```

### Repository

Directly:

```kotlin
maven {
    url = uri("https://maven.pkg.github.com/navikt/aap-avro")
    credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
    }
}
```

.. or through mirror:

```kotlin
repositories {
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")
}
```

# Contact

![img](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) #aap-dev