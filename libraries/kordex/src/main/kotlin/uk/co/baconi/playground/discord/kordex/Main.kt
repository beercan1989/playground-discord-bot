package uk.co.baconi.playground.discord.kordex

import com.kotlindiscord.kord.extensions.ExtensibleBot
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration

suspend fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.kordex.Main")
    logger.info("Starting kordex server")

    val kordex = ExtensibleBot(config.token) {
        chatCommands {
            enabled = true
        }
        extensions {
            add(::LoggingExtension)
            add(::PingPongExtension)
        }
    }

    kordex.start()
}