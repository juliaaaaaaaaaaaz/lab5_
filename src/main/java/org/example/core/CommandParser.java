package org.example.core;

import org.example.data.Messages;
import org.example.exceptions.CommandException;

import java.util.function.Supplier;

/**
 * Класс для разбора и выполнения команд, введенных пользователем.
 * Делегирует исполнение команд классу CommandExecutor.
 */
public class CommandParser {
    private final CommandExecutor commandExecutor;

    /**
     * Конструктор класса CommandParser.
     *
     * @param commandExecutor Экземпляр исполнителя команд для делегирования выполнения.
     */
    public CommandParser(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    /**
     * Разбирает и исполняет команду из введенной пользователем строки.
     *
     * @param commandLine Строка, содержащая команду и ее аргументы.
     * @param inputSupplier Поставщик дополнительного ввода от пользователя.
     * @return Результат выполнения команды.
     * @throws CommandException Исключение, выбрасываемое в случае ошибки в команде.
     */
    public String parseAndExecute(String commandLine, Supplier<String> inputSupplier) throws CommandException {
        if (commandLine == null || commandLine.trim().isEmpty()) {
            return Messages.NO_COMMAND_PROVIDED;
        }

        return commandExecutor.executeCommand(commandLine, inputSupplier);
    }
}
