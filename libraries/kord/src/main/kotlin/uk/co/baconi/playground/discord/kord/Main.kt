package uk.co.baconi.playground.discord.kord

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.behavior.reply
import dev.kord.core.event.Event
import dev.kord.core.event.gateway.DisconnectEvent
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration

suspend fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.kord.Main").apply {
        info("Starting kord server")
    }

    val kord = Kord(config.token)

    kord.on<MessageCreateEvent> {
        when {
            message.author?.isBot != false -> return@on
            message.content.startsWith("!ping") -> {
                logger.debug("Received ping via shortcut")
                message.reply { content = "pong!" }
            }
            message.content.startsWith("${config.applicationMention} ping") -> {
                logger.debug("Received ping via mention")
                message.reply { content = "pong!" }
            }
        }
    }

    kord.createGlobalChatInputCommand("ping", "Its just a bit of Ping, Pong!")

    kord.on<ChatInputCommandInteractionCreateEvent> {
        when {
            interaction.user.isBot -> return@on
            interaction.command.rootName != "ping" -> return@on
            else -> interaction.respondPublic { content = "pong!" }
        }
    }

    kord.on<ReadyEvent> {
        logger.info("Ready to go as {} aka {} for {}", self.id, self.username, guildIds)
    }

    kord.on<GuildCreateEvent> {
        logger.info("Guild now available {} aka {}", guild.id, guild.name)
    }

    kord.on<DisconnectEvent> {
        when(this) {
            is DisconnectEvent.DetachEvent -> logger.info("Detached from shared={}", shard)
            else -> logger.error("Disconnected: {}", this)
        }
    }

    kord.on<Event> {
        logger.trace("Received event: {}", this)
    }

    kord.login {
        logger.info("Login")
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}