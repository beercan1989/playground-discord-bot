package uk.co.baconi.playground.discord.jda

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.slf4j.LoggerFactory
import uk.co.baconi.playground.discord.common.BotConfiguration

class PingPongEventListener(private val config: BotConfiguration) : ListenerAdapter() {

    companion object {
        private val logger = LoggerFactory.getLogger(PingPongEventListener::class.java)

        fun registerPingPongCommand(jda: JDA) {
            jda.updateCommands()
                .addCommands(Commands.slash("ping", "Its a Ping, Pong slash command"))
                .queue()
        }
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        when {
            event.author.isBot -> return
            event.message.author.isBot -> return
            event.message.contentRaw.equals("!ping", true) -> {
                logger.debug("Received ping via shortcut")
                event.message.reply("pong!").queue()
            }
            event.message.contentRaw.equals("${config.applicationMention} ping", true) -> {
                logger.debug("Received ping via mention")
                event.message.reply("pong!").queue()
            }
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when {
            event.name != "ping" -> return
            event.user.isBot -> return
            else -> {
                logger.debug("Received ping via slash command")
                event.reply("pong!").queue()
            }
        }
    }
}