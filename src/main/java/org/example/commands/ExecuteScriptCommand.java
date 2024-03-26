package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.core.CommandExecutor;
import org.example.data.Messages;
import org.example.di.DIContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Команда для чтения и выполнения скрипта из указанного файла.
 * Предотвращает рекурсивное выполнение скриптов.
 */
public class ExecuteScriptCommand implements CommandWithArgs {
    private final DIContainer diContainer;
    private static final Set<String> scriptsBeingExecuted = new HashSet<>();

    /**
     * Конструктор команды ExecuteScript.
     *
     * @param diContainer Контейнер зависимостей, используемый для доступа к исполнителю команд.
     */
    public ExecuteScriptCommand(DIContainer diContainer) {
        this.diContainer = diContainer;
    }

    /**
     * Выполняет скрипт, проверяет наличие рекурсии.
     *
     * @param args Имя файла скрипта.
     * @param inputSupplier Поставщик ввода пользователя.
     * @return Сообщение о выполнении скрипта.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        if (scriptsBeingExecuted.contains(args)) {
            return "Recursion detected. Skipping script: " + args;
        }

        try {
            scriptsBeingExecuted.add(args); // Добавление имени скрипта перед выполнением
            return executeScript(args);
        } catch (FileNotFoundException e) {
            return "File not found: " + args;
        } finally {
            scriptsBeingExecuted.remove(args); // Удаление имени скрипта после выполнения
        }
    }

    private String executeScript(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        StringBuilder result = new StringBuilder();

        CommandExecutor commandExecutor = diContainer.getService(CommandExecutor.class);

        while (fileScanner.hasNextLine()) {
            String commandLine = fileScanner.nextLine().trim();
            if (commandLine.isEmpty() || commandLine.startsWith("//"))
                continue; // Пропускаем пустые строки и комментарии

            // Обработка ввода для команд, требующих дополнительных вводов.
            Supplier<String> fileInputSupplier = () -> fileScanner.hasNextLine() ? fileScanner.nextLine() : "";

            // Выполнение команды и добавление результата в общий результат.
            String output = commandExecutor.executeCommand(commandLine, fileInputSupplier);
            result.append(output).append(System.lineSeparator());
        }
        fileScanner.close();
        return result +"\n" + Messages.SCRIPT_SUCCESS;
    }
}
