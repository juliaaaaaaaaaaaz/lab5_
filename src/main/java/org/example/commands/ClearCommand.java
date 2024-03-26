package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;
import org.example.data.Messages;


/**
 * Команда для очистки коллекции от всех элементов.
 */
public class ClearCommand implements CommandWithoutArgs {

    private final LabWorkCollection labWorkCollection;
    /**
     * Конструктор команды Clear.
     *
     * @param labWorkCollection Коллекция, которая будет очищена.
     */
    public ClearCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Очищает коллекцию от всех элементов.
     *
     * @return Сообщение об успешной очистке коллекции.
     */
    @Override
    public String execute() {
        labWorkCollection.clear();
        return Messages.LAB_WORK_SUCCESS_CLEAR;
    }
}
