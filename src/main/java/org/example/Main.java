package org.example;

import org.example.core.*;
import org.example.data.Messages;
import org.example.di.DIContainer;
import org.example.exceptions.CommandException;


import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Основной класс приложения, который запускает пользовательский интерфейс и обработку команд.
 */
public class Main {
    /**
     * Точка входа в программу. Инициализирует необходимые компоненты и входит в цикл обработки команд пользователя.
     *
     * @param args аргументы командной строки, не используются в данной программе.
     */
    public static void main(String[] args) {
        DIContainer diContainer = new DIContainer();

        Printer printer = diContainer.getService(Printer.class);
        CommandParser commandParser = diContainer.getService(CommandParser.class);


        printer.println(Messages.WELCOME);
        printer.println(Messages.ENTER_HELP);

        Supplier<String> inputSupplier = () -> {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        };

        while (true) try {
            printer.print("> ");
            String commandLine = inputSupplier.get();

            try {
                String result = commandParser.parseAndExecute(commandLine, inputSupplier);
                if (result != null && !result.isEmpty()) {
                    printer.println(result);
                }
            } catch (CommandException e) {
                printer.println(Messages.ERROR + e.getMessage());
            }
        } catch (Exception e) {
            printer.println(Messages.ERROR + e.getMessage());
        }
    }
}