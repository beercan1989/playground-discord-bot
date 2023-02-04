package uk.co.baconi.playground.discord.jda

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent.MESSAGE_CONTENT
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration
import uk.co.baconi.playground.discord.jda.PingPongEventListener.Companion.registerPingPongCommand

fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.jda.Main").apply {
        info("JDA server is starting")
    }

    JDABuilder
        .createDefault(config.token)
        .enableIntents(MESSAGE_CONTENT)
        .addEventListeners(LoggingEventListener())
        .addEventListeners(PingPongEventListener(config))
        .build()
        .apply(::registerPingPongCommand)
        .awaitReady()

    logger.info("JDA server is ready")
}