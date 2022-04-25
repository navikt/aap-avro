![img](logo.jpg)

[![img](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)](https://nav-it.slack.com/app_redirect?channel=C02CW21TBKR)

[![Release](https://jitpack.io/v/com.github.navikt/aap-avero.svg)](https://jitpack.io/#com.github.navikt/aap-avro)

## 📚 Usage

Include Jitpack package repository
```kotlin
repositories {
  maven("https://jitpack.io")
}
```

#### Medlemskap (LovMe)

Topic `aap.medlem.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-medlem.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-medlem.yml) <br/>

```kotlin
implementation("com.github.navikt.aap-avro:medlem:X.X.X")
```

#### Søker (internal domain)

Topic `aap.sokere.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-søkere.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-s%C3%B8kere.yml)

```kotlin
implementation("com.github.navikt.aap-avro:sokere:X.X.X")
```

#### Manuell oppgave (internal domain)

Topic `aap.manuell.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-manuell.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-manuell.yml)

```kotlin
implementation("com.github.navikt.aap-avro:manuell:X.X.X")
```

#### Inntekter (internal domain)

Topic `aap.inntekter.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-inntekter.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-inntekter.yml)

```kotlin
implementation("com.github.navikt.aap-avro:inntekter:X.X.X")
```
## 🚧 Development

This library uses semantic versioning `<major>.<minor>.<patch>`

To release a **patch**, simply commit to main.

To release a **minor** or **major** version, include `(MINOR)` or `(MAJOR)` in the git commit (including brackets).
