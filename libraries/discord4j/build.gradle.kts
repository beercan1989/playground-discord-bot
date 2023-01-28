plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
}

application {
    mainClass.set("uk.co.baconi.playground.discord.discord4j.MainKt")
}