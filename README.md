# JPM – Java Project Manager

**Unified Dependency & Project Management CLI for Maven & Gradle**

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 🚀 Overview

**JPM (Java Project Manager)** is an **open-source CLI tool** that simplifies managing Java projects.
It unifies workflows across build systems (**Maven** and **Gradle**) and provides commands to:

* Manage dependencies (**add, remove, upgrade, downgrade, show**)
* Inspect projects (**list modules, display dependency tree, detect unused deps**)
* Initialize new projects with common frameworks (**Spring Boot, Micronaut, Dropwizard, Plain Java**)
* Package as a **single JAR** or **native binary** for easy distribution

---

## ✨ Features

* **Dependency Management**

    * `jpm deps add` → Add dependencies safely (with version selection & backups)
    * `jpm deps remove` → Remove dependencies (with usage checks)
    * `jpm deps upgrade` / `downgrade` → Bump dependency versions
    * `jpm deps show` → Display declared or unused dependencies
    * `jpm deps tree` → View full dependency tree

* **Module Management**

    * `jpm module find` → List modules in multi-module projects

* **Project Initialization**

    * `jpm init java` → Bootstrap plain Java project
    * `jpm init spring` → Generate Spring Boot project
    * `jpm init micronaut` → Generate Micronaut project
    * `jpm init dropwizard` → Generate Dropwizard project

* **Cross-Build Support**

    * Maven and Gradle adapters via pluggable architecture
    * Designed for future expansion (SBT, Bazel…)

* **Distribution**

    * Runs as a **shaded JAR** (`java -jar jpm-cli.jar`)
    * Compiles to a **native executable** via GraalVM (`./jpm-cli`)

---

## 🏗 Project Structure

```
jpm/
├─ pom.xml                # parent POM
├─ jpm-core/              # Core business logic & APIs
├─ jpm-adapter-maven/     # Maven adapter (POM parsing & editing)
├─ jpm-adapter-gradle/    # Gradle adapter (build.gradle support)
├─ jpm-init/              # Project initializers (Spring, Micronaut…)
├─ jpm-cli/               # Picocli-based CLI commands
└─ jpm-integration-tests/ # Black-box testing with sample projects
```

---

## 🔧 Installation

### Option 1: Run from Source

```bash
git clone https://github.com/<your-username>/jpm.git
cd jpm
mvn -DskipTests package
java -jar jpm-cli/target/jpm-cli-shaded.jar --help
```

### Option 2: Native Binary (macOS ARM64 example)

```bash
mvn -pl jpm-cli -Pnative -DskipTests package
./jpm-cli/target/jpm-cli --help
```

---

## 💡 Usage Examples

List project modules:

```bash
jpm module find -bt maven .
```

Add a dependency:

```bash
jpm deps add --group-id com.google.guava --artifact-id guava --version 33.2.1-jre
```

Initialize a Micronaut project:

```bash
jpm init micronaut --group-id org.example --artifact-id demo-service
```

---

## 🛠 Tech Stack

* **Java 21**
* **Maven (multi-module)**
* **Picocli** for CLI parsing
* **GraalVM** for native compilation

---

## 📝 License

This project is released under the [MIT License](LICENSE).
