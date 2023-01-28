package uk.co.baconi.playground.discord.common

import com.typesafe.config.ConfigFactory

// TODO - Look at Kotlinx Serialisation support
data class BotConfiguration(val applicationId: String, val token: String) {

    // String used to detect mentions of this application / bot
    val applicationMention = "<@$applicationId>"

    constructor() : this(
        applicationId = config.getString("applicationId"),
        token = config.getString("token")
    )

    companion object {
        private val config = ConfigFactory.load().getConfig("uk.co.baconi.playground.discord")
    }
}