package uk.co.baconi.playground.discord.javacord

import org.javacord.api.DiscordApi
import org.javacord.api.event.interaction.SlashCommandCreateEvent
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.interaction.SlashCommand
import org.javacord.api.listener.interaction.SlashCommandCreateListener
import org.javacord.api.listener.message.MessageCreateListener
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration

class PingPongListener(private val config: BotConfiguration) : MessageCreateListener, SlashCommandCreateListener {

    companion object {

        private val logger = LoggerFactory.getLogger(PingPongListener::class.java)

        fun DiscordApi.registerPingPongCommand() = apply {
            SlashCommand.with("ping", "A simple ping pong command!").createGlobal(this).join()
        }
    }

    override fun onMessageCreate(event: MessageCreateEvent) {
        when {
            event.messageAuthor.isBotUser-> return
            event.messageContent.equals("!ping", true) -> {
                logger.debug("Received ping via shortcut")
                event.message.reply("pong!")
            }
            event.messageContent.equals("${config.applicationMention} ping", true) -> {
                logger.debug("Received ping via mention")
                event.message.reply("pong!")
            }
        }
    }

    override fun onSlashCommandCreate(event: SlashCommandCreateEvent) {
        when {
            event.slashCommandInteraction.commandName != "ping" -> return
            event.slashCommandInteraction.user.isBot -> return
            else -> {
                logger.debug("Received ping via slash command")
                event.slashCommandInteraction
                    .createImmediateResponder()
                    .setContent("pong!")
                    .respond()
            }
        }
    }
}