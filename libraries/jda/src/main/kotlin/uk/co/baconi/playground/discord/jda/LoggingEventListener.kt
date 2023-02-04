package uk.co.baconi.playground.discord.jda

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.slf4j.LoggerFactory

class LoggingEventListener : EventListener {

    companion object {
        private val logger = LoggerFactory.getLogger(LoggingEventListener::class.java)
    }

    override fun onEvent(event: GenericEvent) {
        when(event) {

            is ReadyEvent -> logger.info(
                "Ready to go with guilds, total {}, available {}, unavailable {}",
                event.guildTotalCount,
                event.guildAvailableCount,
                event.guildUnavailableCount
            )

            is GuildAvailableEvent -> logger.info("Guild now available {} aka {}", event.guild.id, event.guild.name)
            is GuildLeaveEvent -> logger.info("Guild has been left {} aka {}", event.guild.id, event.guild.name)
            is UnavailableGuildLeaveEvent -> logger.info("Unavailable Guild has been left {}", event.guildId)

            else -> logger.trace("Received event: {}", event)
        }
    }
}