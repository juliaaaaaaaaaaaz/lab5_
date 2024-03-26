package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;

/**
 * Команда для вывода информации о коллекции.
 */
public class InfoCommand implements CommandWithoutArgs {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды Info.
     *
     * @param labWorkCollection Коллекция, о которой выводится информация.
     */
    public InfoCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выводит информацию о коллекции.
     *
     * @return Информация о коллекции.
     */
    @Override
    public String execute() {
        return labWorkCollection.getInfo();
    }
}
