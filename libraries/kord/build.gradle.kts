plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
    implementation("dev.kord:kord-core:0.8.0-M17")
}

application {
    mainClass.set("uk.co.baconi.playground.discord.kord.MainKt")
}