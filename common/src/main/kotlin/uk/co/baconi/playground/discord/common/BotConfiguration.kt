package uk.co.baconi.playground.discord.common

import com.typesafe.config.ConfigFactory

data class BotConfiguration(val token: String) {

    constructor() : this(
        token = config.getString("token")
    )

    companion object {
        private val config = ConfigFactory.load().getConfig("uk.co.baconi.playground.discord")
    }
}