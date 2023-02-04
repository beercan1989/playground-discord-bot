plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
    implementation("org.javacord:javacord:3.7.0")
    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.19.0")
}

application {
    mainClass.set("uk.co.baconi.playground.discord.javacord.MainKt")
}