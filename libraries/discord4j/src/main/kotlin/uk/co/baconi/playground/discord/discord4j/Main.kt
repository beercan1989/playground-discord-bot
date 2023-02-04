package uk.co.baconi.playground.discord.discord4j

import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono.empty
import reactor.core.publisher.Mono.fromRunnable
import uk.co.baconi.playground.discord.common.BotConfiguration

fun main() {

    val config = BotConfiguration()

    val logger = LoggerFactory.getLogger("uk.co.baconi.playground.discord.discord4j.Main").apply {
        info("Discord4J server is starting")
    }

    val client = DiscordClient.create(config.token)

    val login = client.withGateway { gateway: GatewayDiscordClient ->

        gateway.on(ReadyEvent::class.java) { event ->
            fromRunnable<Void> {
                logger.info("Discord4J server is ready as {}#{}", event.self.username, event.self.discriminator)
            }
        }.subscribe()

        gateway.on(MessageCreateEvent::class.java) { event ->
            when {
                event.message.author.isEmpty -> empty()
                event.message.author.get().isBot -> empty()
                event.message.content.equals("!ping", true) -> {
                    logger.debug("Received ping via shortcut")
                    event.message.channel.flatMap { channel ->
                        channel.createMessage("pong!")
                    }
                }
                event.message.content.equals("${config.applicationMention} ping", true) -> {
                    logger.debug("Received ping via mention")
                    event.message.channel.flatMap { channel ->
                        channel.createMessage("pong!")
                    }
                }
                else -> empty()
            }
        }.subscribe()

        gateway.on(ApplicationCommandInteractionEvent::class.java) { event ->
            when {
                event.commandName != "ping" -> empty()
                else -> {
                    logger.debug("Received ping via slash command")
                    event.reply("pong!")
                }
            }
        }.subscribe()

        gateway.onDisconnect()
    }

    login.block()
}