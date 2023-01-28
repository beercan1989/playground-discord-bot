plugins {
    id("playground-discord-bot.common")
    application
}

repositories {
    maven("https://maven.kotlindiscord.com/repository/maven-public/") // Kord Extensions isn't in the Maven Central
}

dependencies {
    implementation(project(":common"))
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.2-RC1")
}

application {
    mainClass.set("uk.co.baconi.playground.discord.kordex.MainKt")
}