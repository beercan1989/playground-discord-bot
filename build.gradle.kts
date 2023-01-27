import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "uk.co.baconi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.5")

    // Test definitions and running
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")

    // Assertions
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")

    // Mocking
    testImplementation("io.mockk:mockk:1.13.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("uk.co.baconi.MainKt")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.withType<Wrapper>().configureEach {
    distributionType = Wrapper.DistributionType.ALL
}