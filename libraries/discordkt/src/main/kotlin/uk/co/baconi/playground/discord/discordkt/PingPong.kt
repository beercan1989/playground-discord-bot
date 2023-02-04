package uk.co.baconi.playground.discord.discordkt

import me.jakejmattson.discordkt.commands.commands
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger(PingPong::class.java)

fun pingPong() = commands("Utility") {
    slash(name = "ping", description = "Its a Ping, Pong slash command") {
        execute {
            logger.debug("Received ping via slash command")
            respondPublic("pong!")
        }
    }
}

object PingPong