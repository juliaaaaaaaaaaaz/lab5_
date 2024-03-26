package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;

/**
 * Команда для выхода из программы.
 */
public class ExitCommand implements CommandWithoutArgs {

    /**
     * Выполняет выход из программы.
     *
     * @return Пустая строка, так как выход из программы происходит до возвращения значения.
     */
    @Override
    public String execute() {
        System.exit(0);
        return "";
    }
}
