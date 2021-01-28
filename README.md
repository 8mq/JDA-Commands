<h1 align="center">JDA Command System</h1>

<p align="center">A Command library for <a href="https://github.com/DV8FromTheWorld/JDA">JDA</a> using java 8 lambdas</p>

### Example Usage:
```java
public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault("").addEventListeners(new Main()).build();
        
        Command.create("Say", new String[]{"say"}, "say <message>", (arguments, event) -> 
                ((MessageReceivedEvent) event).getChannel().sendMessage(String.join(" ", arguments)).queue()
        ).register();

        Command.create("Prefix", new String[]{"prefix", "p"}, "prefix <character>", (arguments, event) -> {
            if (arguments.length == 1) {
                Command.prefix = arguments[0];
                ((MessageReceivedEvent) event).getChannel().sendMessage("Prefix set to " + arguments[0]).queue();
            } else ((MessageReceivedEvent) event).getChannel().sendMessage("Wrong syntax").queue();
        }).register();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot())
            Command.parse(event.getMessage().getContentRaw(), event);
    }
}
```

### Installation

#### Maven
```xml
<repository>
	<id>jitpack.io</id>
        <name>Jitpack</name>
	<url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
	<groupId>com.github.8mq</groupId>
	<artifactId>JDA-Commands</artifactId>
	<version>1.0</version>
</dependency>
```

#### Gradle
```gradle
repositories {
	maven { url 'https://jitpack.io' }
}
```
```gradle
dependencies {
	implementation 'com.github.8mq:JDA-Commands:1.0'
}
```

