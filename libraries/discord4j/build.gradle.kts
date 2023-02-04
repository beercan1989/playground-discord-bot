plugins {
    id("playground-discord-bot.common")
    application
}

dependencies {
    implementation(project(":common"))
    implementation("com.discord4j:discord4j-core:3.2.3")
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.14.2")) // security patching discord4j-core
    implementation(platform("io.projectreactor:reactor-bom:2020.0.27")) // security patching discord4j-core
}

application {
    mainClass.set("uk.co.baconi.playground.discord.discord4j.MainKt")
}