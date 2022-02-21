# AAP Avro Skjemaer

[![Generic badge](https://img.shields.io/badge/medlem-1.1.3-blue.svg)](https://github.com/navikt/aap-avro/packages/1262528?version=1.1.3)
[![Generic badge](https://img.shields.io/badge/vedtak-1.1.6-blue.svg)](https://github.com/navikt/aap-avro/packages/1262527?version=1.1.6)

[![img](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)](https://nav-it.slack.com/app_redirect?channel=C02CW21TBKR)

## 📚 Usage

#### Medlemskap

Topic `aap.medlem.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-medlem.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-medlem.yml) </br>

```kotlin
implementation("no.nav.aap.avro:medlem:1.1.3")
```

#### Søker (internal domain)

Topic `aap.sokere.v1` &nbsp;&nbsp;&nbsp;&nbsp;
Definition [topic-søkere.yml](https://github.com/navikt/aap-vedtak/blob/main/.nais/topic-s%C3%B8kere.yml)

```kotlin
implementation("no.nav.aap.avro:vedtak:1.1.5")
```

<details>
<summary>Gradle Repository 🪐</summary>

🔑 Private GitHub package registry:

```kotlin
maven {
    url = uri("https://maven.pkg.github.com/navikt/aap-avro")
    credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
    }
}
```

🪞 Mirror:

```kotlin
repositories {
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")
}
```

</details>

## 🚧 Development

Separate artifacts will be built for each topic schema. 
This is to avoid _breaking changes_ for unrelated implementations.

* Changes in [/medlem](/medlem) will trigger a **new** version of medlem schema on **commit**.
* Changes in [/vedtak](/vedtak) will trigger a **new** version of vedtak schema on **commit**.

#### Semantic Versioning
SemVer is used for automatic updates through e.g. dependabot.
It will also allow differing between _major_ (breaking changes), _minor_ and _patch_ releases.
Versions will be deduced through tags with format `<major>.<minor>.<patch>-<schema>` 

##### Example 
Tag `1.1.5-vedtak` is used by the `vedtak` workflow to find its previous version, 
and will strip its `postfix` to upgrade to the next semantic version.
If your commit contains the term `minor` or `major` the respectively semver will be upgraded.
No special term will default to `patch`.

#### Auto update README.md
A shell script will replace specific line numbers in the README.md file with the updated badge.
