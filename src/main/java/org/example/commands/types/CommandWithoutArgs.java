package org.example.commands.types;

/**
 * Интерфейс для команд, не требующих дополнительных аргументов или ввода от пользователя.
 */
public interface CommandWithoutArgs {
    /**
     * Выполняет команду.
     *
     * @return Результат выполнения команды в виде строки.
     */
    String execute();
}
