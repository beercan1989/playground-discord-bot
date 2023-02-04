# Playground Discord Bot

Playing around with what can be done with/for discord bots.

## Project configuration
For things like secrets, these are managed via environment variables set when the run command is used.

These can be managed within say IntelliJ IDEA by using the EnvFile plugin and a `.env` file in the root of the project.

### Required (to start application)
```
# The application id for the Discord application you want to run this under.
APPLICATION_ID=123

# The bot token for the Discord application you want to run this under.
TOKEN=abc-123
```

### Optional
```
# Used to toggle the application logging, enable trace to see all events recieved. 
# Default: DEBUG
LOGGING_LEVEL=TRACE
```

## Discord permissions required
### Privileged Gateway Intents:
* Message Content Intent

### General Permissions:
* Read Messages / View Channels

### Text Permissions:
* Send Messages

## So what am I doing?

Probably just having a quick look at the various libraries, to see how they feel.

### Kord
Kord is a coroutine-based, modularized implementation of the Discord API, written 100% in Kotlin.
* https://github.com/kordlib/kord/wiki/Getting-Started
* https://github.com/kordlib/kordx.commands
* https://github.com/kordlib/kordx.emoji

See [libraries/kord/README.md](libraries/kord/README.md) for more

### Kordex
Kord Extensions is an addon for the excellent Kord library. It intends to provide a framework for larger bot projects, with easy-to-use commands, rich argument parsing and event handling, wrapped up into individual extension classes.
* https://kordex.kotlindiscord.com/

See [libraries/kordex/README.md](libraries/kordex/README.md) for more

### Discord KT
DiscordKt is a framework for creating Discord bots entirely in Kotlin. It uses Kord for direct interactions with the Discord API.
* https://discordkt.github.io

See [libraries/discordkt/README.md](libraries/discordkt/README.md) for more

### Discord4J
Discord4J is a fast, powerful, reactive library to enable quick and easy development of Discord bots for Java, Kotlin, and other JVM languages using the official Discord Bot API.
* https://docs.discord4j.com/

See [libraries/discord4j/README.md](libraries/discord4j/README.md) for more

### Javacord
An easy to use multithreaded library for creating Discord bots in Java.
* https://github.com/Javacord/Javacord

See [libraries/javacord/README.md](libraries/javacord/README.md) for more

### JDA
JDA strives to provide a clean and full wrapping of the Discord REST api and its Websocket-Events for Java. This library is a helpful tool that provides the functionality to create a discord bot in java.
* https://github.com/DV8FromTheWorld/JDA

See [libraries/jda/README.md](libraries/jda/README.md) for more
