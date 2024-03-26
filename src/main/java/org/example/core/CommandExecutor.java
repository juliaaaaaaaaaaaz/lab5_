package org.example.core;

import org.example.data.Messages;
import org.example.di.DIContainer;
import org.example.commands.types.CommandWithArgs;
import org.example.commands.types.CommandWithoutArgs;

import java.util.function.Supplier;

/**
 * Класс, отвечающий за исполнение команд.
 * Использует DIContainer для получения экземпляров команд и выполняет их с учетом входных данных.
 */
public class CommandExecutor {
    private final DIContainer diContainer;

    /**
     * Конструктор класса CommandExecutor.
     *
     * @param diContainer Контейнер зависимостей для получения экземпляров команд.
     */
    public CommandExecutor(DIContainer diContainer) {
        this.diContainer = diContainer;
    }

    /**
     * Выполняет команду, указанную в строке командной строки.
     *
     * @param commandLine Строка командной строки.
     * @param inputSupplier Поставщик дополнительного ввода от пользователя.
     * @return Результат выполнения команды.
     */
    public String executeCommand(String commandLine, Supplier<String> inputSupplier) {
        String[] parts = commandLine.split(" ", 2);
        String commandName = parts[0];
        Object command = diContainer.getCommand(commandName);

        if (command instanceof CommandWithArgs) {
            return ((CommandWithArgs) command).execute(parts.length > 1 ? parts[1] : "", inputSupplier);
        } else if (command instanceof CommandWithoutArgs) {
            return ((CommandWithoutArgs) command).execute();
        }

        return Messages.COMMAND_NOT_RECOGNIZED + commandName;
    }
}
