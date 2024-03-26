package org.example.commands;

import org.example.core.LabWorkCollection;
import org.example.core.LabWorkReader;
import org.example.data.LabWork;
import org.example.commands.types.CommandWithArgs;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для добавления нового элемента в коллекцию.
 */
public class AddCommand implements CommandWithArgs {
    private final LabWorkCollection labWorkCollection;
    private final LabWorkReader labWorkReader;

    /**
     * Конструктор команды Add.
     *
     * @param labWorkCollection Коллекция, куда будет добавлен новый элемент.
     * @param labWorkReader Считыватель данных LabWork.
     */
    public AddCommand(LabWorkCollection labWorkCollection, LabWorkReader labWorkReader) {
        this.labWorkCollection = labWorkCollection;
        this.labWorkReader = labWorkReader;
    }

    /**
     * Выполняет добавление нового элемента в коллекцию.
     *
     * @param args Аргументы команды.
     * @param inputSupplier Поставщик ввода пользователя.
     * @return Сообщение о результате добавления.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        long id = LabWorkReader.generateId();
        LabWork labWork = labWorkReader.readLabWork(id, inputSupplier);
        labWorkCollection.add(labWork);
        return Messages.LAB_WORK_SUCCESS_ADD;
    }
}
