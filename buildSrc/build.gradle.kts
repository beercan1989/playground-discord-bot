plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    // Versions the 'kotlin("jvm")' plugin in the 'playground-discord-bot.common' convention plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
}