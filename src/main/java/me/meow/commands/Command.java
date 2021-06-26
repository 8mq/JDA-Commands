package me.meow.commands;

import net.dv8tion.jda.api.events.Event;

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
    public final BiConsumer<String[], Event> consumer;

    private Command(String name, String[] aliases, String usage, BiConsumer<String[], Event> consumer) {
        this.name = name;
        this.aliases = aliases;
        this.usage = usage;
        this.consumer = consumer;

        register();
    }

    public static Command create(String name, String[] aliases, String usage, BiConsumer<String[], Event> consumer) {
        return new Command(name, aliases, usage, consumer);
    }

    public void register() {
        commands.add(this);
    }

    public void unregister() {
        commands.remove(this);
    }

    public static void parse(String message, Event event) {
        if (message.startsWith(prefix)) {
            message = message.substring(prefix.length());
            String[] split = message.split(" ");
            commands.stream()
                    .filter(command -> Arrays.stream(command.aliases).anyMatch(split[0]::equalsIgnoreCase))
                    .forEach(command -> command.consumer.accept(Arrays.copyOfRange(split, 1, split.length), event));
        }
    }
}
