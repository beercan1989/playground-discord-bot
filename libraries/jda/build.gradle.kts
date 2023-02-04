plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
    implementation("net.dv8tion:JDA:5.0.0-beta.3")
}

application {
    mainClass.set("uk.co.baconi.playground.discord.jda.MainKt")
}