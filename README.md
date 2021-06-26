<h1 align="center">JDA Command System</h1>

<p align="center">A Command library for <a href="https://github.com/DV8FromTheWorld/JDA">JDA</a> using java 8 lambdas</p>

### Usage

Create a command using `Command.create`
```java
Command cmd = Command.create(biConsumer, name, usage, aliases);
```

### Example
```java
public class Main extends ListenerAdapter {
    Command say = Command.create((arguments, event) -> {
        event.getChannel().sendMessage(String.join(" ", arguments)).queue();
    }, "Say", "say <message>", "say");

    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault("").addEventListeners(new Main()).build();
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
	<version>VERSION</version>
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
	implementation 'com.github.8mq:JDA-Commands:VERSION'
}
```

Replace `VERSION` with the latest version.
