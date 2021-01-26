package me.meow.commands;

import net.dv8tion.jda.api.events.Event;

import java.util.Arrays;
import java.util.function.BiConsumer;

public final class Command {
    public static String prefix = "!";
    protected final String name;
    protected final String[] aliases;
    protected final String usage;
    private final BiConsumer<String[], Event> consumer;

    private Command(String name, String[] aliases, String usage, BiConsumer<String[], Event> consumer) {
        this.name = name;
        this.aliases = aliases;
        this.usage = usage;
        this.consumer = consumer;
    }

    public static void parse(String message, Event event) {
        if (message.startsWith(prefix)) {
            message = message.substring(prefix.length());
            String[] split = message.split(" ");
            CommandRegistry.getSet().stream()
                    .filter(command -> Arrays.stream(command.aliases).anyMatch(split[0]::equalsIgnoreCase))
                    .forEach(command -> {
                        command.consumer.accept(Arrays.copyOfRange(split, 1, split.length), event);
                    });
        }
    }

    public static Command create(String name, String[] aliases, String usage, BiConsumer<String[], Event> consumer) {
        return new Command(name, aliases, usage, consumer);
    }

    public void register() {
        CommandRegistry.register(this);
    }

    public void unregister() {
        CommandRegistry.unregister(this);
    }
}
