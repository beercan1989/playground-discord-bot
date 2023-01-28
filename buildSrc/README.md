# Just what is this buildSrc thing?

I'm basically using it to share configuration between subprojects via the Gradle convention plugin mechanism.

* [playground-discord-bot.common](src/main/kotlin/playground-discord-bot.common.gradle.kts) convention plugin to manage the common configuration and dependencies, like how one would do in Maven using inheritance from parent modules.

## Official Gradle description
> Use buildSrc to abstract imperative logic  
> 
> Complex build logic is usually a good candidate for being encapsulated either as custom task or binary plugin. Custom task and plugin implementations should not live in the build script. It is very convenient to use buildSrc for that purpose as long as the code does not need to be shared among multiple, independent projects.  
> 
> The directory buildSrc is treated as an included build. Upon discovery of the directory, Gradle automatically compiles and tests this code and puts it in the classpath of your build script. For multi-project builds there can be only one buildSrc directory, which has to sit in the root project directory. buildSrc should be preferred over script plugins as it is easier to maintain, refactor and test the code.
> 
> buildSrc uses the same source code conventions applicable to Java and Groovy projects. It also provides direct access to the Gradle API. Additional dependencies can be declared in a dedicated build.gradle under buildSrc.
> 
> https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources