<h1 align="center"><a href="https://github.com/DV8FromTheWorld/JDA">JDA</a> Command System</h1>

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