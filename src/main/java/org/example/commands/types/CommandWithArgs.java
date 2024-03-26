package org.example.commands.types;

import java.util.function.Supplier;
/**
 * Интерфейс для команд, требующих аргументов и/или дополнительного ввода от пользователя.
 */
public interface CommandWithArgs {
    /**
     * Выполняет команду с заданными аргументами.
     *
     * @param args Аргументы, переданные в команду.
     * @param inputSupplier Поставщик дополнительного ввода от пользователя.
     * @return Результат выполнения команды в виде строки.
     */
    String execute(String args, Supplier<String> inputSupplier);
}
