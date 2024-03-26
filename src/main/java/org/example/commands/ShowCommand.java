package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;

/**
 * Команда для отображения всех элементов коллекции.
 */
public class ShowCommand implements CommandWithoutArgs {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды Show.
     *
     * @param labWorkCollection Коллекция для отображения.
     */
    public ShowCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Отображает все элементы коллекции.
     *

     * @return Строка с представлением всех элементов коллекции.
     */
    @Override
    public String execute() {
        return labWorkCollection.show();
    }
}
