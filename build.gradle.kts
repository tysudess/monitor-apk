plugins {
    kotlin("jvm") version "2.0.21"
}

group = "br.com.monitordenoticias"
version = "4.0.3-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test { useJUnitPlatform() }
