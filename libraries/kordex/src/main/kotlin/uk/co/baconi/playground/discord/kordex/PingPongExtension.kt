package uk.co.baconi.playground.discord.kordex

import com.kotlindiscord.kord.extensions.checks.isNotBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import com.kotlindiscord.kord.extensions.utils.respond

class PingPongExtension : Extension() {

    override val name: String = "PingPong"

    override suspend fun setup() {
        chatCommand {
            name = "ping"
            description = "Its just a bit of Ping, Pong!"
            check { isNotBot() }
            action {
                message.respond("pong!")
            }
        }
        publicSlashCommand {
            name = "ping"
            description = "Its a Ping, Pong slash command!"
            check { isNotBot() }
            action {
                respond {
                    content = "pong!"
                }
            }
        }
    }
}