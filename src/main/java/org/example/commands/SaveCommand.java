package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для сохранения текущего состояния коллекции в файл.
 */
public class SaveCommand implements CommandWithoutArgs {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды Save.
     *
     * @param labWorkCollection Коллекция, которая будет сохранена.
     */
    public SaveCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @return Сообщение об успешном сохранении коллекции.
     */
    @Override
    public String execute() {
        labWorkCollection.saveToFile();
        return Messages.LAB_WORK_SUCCESS_SAVE;
    }
}
