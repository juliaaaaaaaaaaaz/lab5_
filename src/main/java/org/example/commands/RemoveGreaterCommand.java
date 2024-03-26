package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.core.LabWorkCollection;
import org.example.core.LabWorkReader;
import org.example.data.LabWork;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для удаления из коллекции всех элементов, превышающих заданный.
 */
public class RemoveGreaterCommand implements CommandWithArgs {
    private final LabWorkCollection labWorkCollection;
    private final LabWorkReader labWorkReader;

    /**
     * Конструктор команды RemoveGreater.
     *
     * @param labWorkCollection Коллекция, из которой будут удаляться элементы.
     * @param labWorkReader Читатель объектов LabWork для интерпретации пользовательского ввода.
     */
    public RemoveGreaterCommand(LabWorkCollection labWorkCollection, LabWorkReader labWorkReader) {
        this.labWorkCollection = labWorkCollection;
        this.labWorkReader = labWorkReader;
    }
    /**
     * Выполняет удаление всех элементов, превышающих указанный.
     *
     * @param args Аргументы команды, не используются.
     * @param inputSupplier Поставщик дополнительного пользовательского ввода.
     * @return Сообщение о количестве удаленных элементов.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        LabWork labWorkToCompare = labWorkReader.readLabWork(LabWorkReader.generateId(), inputSupplier);
        int removed = labWorkCollection.removeIf(labWork ->
                labWork.getDiscipline().getPracticeHours() > labWorkToCompare.getDiscipline().getPracticeHours());

        return String.format(Messages.REMOVED_D_ELEMENTS, removed);
    }
}
