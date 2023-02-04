plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
    implementation("me.jakejmattson:DiscordKt:0.23.4") {
        exclude("org.slf4j", "slf4j-simple")
    }
}

application {
    mainClass.set("uk.co.baconi.playground.discord.discordkt.MainKt")
}