package uk.co.baconi.playground.discord.javacord

import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.intent.Intent
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration
import uk.co.baconi.playground.discord.javacord.PingPongListener.Companion.registerPingPongCommand

fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.javacord.Main").apply {
        info("Javacord server is starting")
    }

    val pingPong = PingPongListener(config)

    DiscordApiBuilder()
        .setToken(config.token)
        .addIntents(Intent.MESSAGE_CONTENT)
        .addMessageCreateListener(pingPong)
        .addSlashCommandCreateListener(pingPong)
        .login()
        .join()
        .registerPingPongCommand()

    logger.info("Javacord server is ready")
}