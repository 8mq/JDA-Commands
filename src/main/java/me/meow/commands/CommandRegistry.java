package me.meow.commands;

import java.util.HashSet;
import java.util.Set;

final class CommandRegistry extends HashSet<Command> {
    private static final CommandRegistry INSTANCE = new CommandRegistry();

    public static Set<Command> getSet() {
        return INSTANCE;
    }

    public static <E extends Command> void register(E command) {
        INSTANCE.add(command);
    }

    public static <E extends Command> void unregister(E command) {
        INSTANCE.remove(command);
    }
}
