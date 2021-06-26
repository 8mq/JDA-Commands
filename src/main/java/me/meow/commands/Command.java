package me.meow.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public final class Command {
    public static Set<Command> commands = new HashSet<>();
    public static String prefix = "!";

    public final String name;
    public final String[] aliases;
    public final String usage;
    public final BiConsumer<String[], MessageReceivedEvent> consumer;

    private Command(BiConsumer<String[], MessageReceivedEvent> consumer, String name, String usage, String... aliases) {
        this.consumer = consumer;
        this.name = name;
        this.usage = usage;
        this.aliases = aliases;

        register();
    }

    public static Command create(BiConsumer<String[], MessageReceivedEvent> consumer, String name, String usage, String... aliases) {
        return new Command(consumer, name, usage, aliases);
    }

    public void register() {
        commands.add(this);
    }

    public void unregister() {
        commands.remove(this);
    }

    public static void parse(String message, MessageReceivedEvent event) {
        if (message.startsWith(prefix)) {
            message = message.substring(prefix.length());
            String[] split = message.split(" ");
            commands.stream()
                    .filter(command -> Arrays.stream(command.aliases).anyMatch(split[0]::equalsIgnoreCase))
                    .forEach(command -> command.consumer.accept(Arrays.copyOfRange(split, 1, split.length), event));
        }
    }
}
