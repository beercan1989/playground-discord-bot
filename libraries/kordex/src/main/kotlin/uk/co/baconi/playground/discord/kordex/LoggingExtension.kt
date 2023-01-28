package uk.co.baconi.playground.discord.kordex

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.event.Event
import dev.kord.core.event.gateway.DisconnectEvent
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.guild.GuildCreateEvent
import org.slf4j.LoggerFactory

class LoggingExtension : Extension() {

    companion object {
        private val logger = LoggerFactory.getLogger(LoggingExtension::class.java)
    }

    override val name: String = "Logging"

    override suspend fun setup() {

        event<ReadyEvent> {
            check { failIfNot(logger.isInfoEnabled) }
            action {
                logger.info("Ready to go as {} aka {} for {}", event.self.id, event.self.username, event.guildIds)
            }
        }

        event<GuildCreateEvent> {
            check { failIfNot(logger.isInfoEnabled) }
            action {
                logger.info("Guild now available {} aka {}", event.guild.id, event.guild.name)
            }
        }

        event<DisconnectEvent> {
            check { failIfNot(logger.isInfoEnabled) }
            action {
                when(event) {
                    is DisconnectEvent.DetachEvent -> logger.info("Detached from shared={}", event.shard)
                    else -> logger.error("Disconnected: {}", event)
                }
            }
        }

        event<Event> {
            check { failIfNot(logger.isTraceEnabled) }
            action {
                logger.trace("Received event: {}", event)
            }
        }
    }
}