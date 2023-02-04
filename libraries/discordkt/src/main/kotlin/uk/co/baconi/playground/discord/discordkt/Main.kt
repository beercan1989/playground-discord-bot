package uk.co.baconi.playground.discord.discordkt

import dev.kord.common.annotation.KordPreview
import dev.kord.gateway.Intents
import kotlinx.coroutines.flow.toList
import me.jakejmattson.discordkt.dsl.CommandException
import me.jakejmattson.discordkt.dsl.ListenerException
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.locale.Language
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration

@KordPreview
fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.discordkt.Main").apply {
        info("DiscordKT server is starting")
    }

    bot(config.token) {

        prefix { "!" }

        configure {

            mentionAsPrefix = false

            logStartup = true

            intents = Intents.nonPrivileged
        }

        onException {
            when {
                exception is IllegalArgumentException -> return@onException
                this is CommandException -> logger.error("Exception '${exception::class.simpleName}' in command ${event.command?.name}", exception)
                this is ListenerException -> logger.error("Exception '${exception::class.simpleName}' in listener ${event::class.simpleName}", exception)
            }
        }

        onStart {
            val guilds = kord.guilds.toList().map { it.name }
            logger.info("DiscordKT server is ready, with guilds: {}", guilds)
        }

        localeOf(Language.EN) {
            helpName = "Help"
            helpCategory = "Utility"
            commandRecommendation = "Recommendation: {0}"
        }
    }
}