package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.core.LabWorkCollection;
import org.example.core.LabWorkReader;
import org.example.core.Validator;
import org.example.data.LabWork;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для удаления всех элементов из коллекции, которые меньше заданного.
 */
public class RemoveLowerCommand implements CommandWithArgs {
    private final LabWorkCollection labWorkCollection;
    private final Validator validator;

    /**
     * Конструктор команды RemoveLower.
     *
     * @param labWorkCollection Коллекция, из которой будут удаляться элементы.
     * @param validator Валидатор данных.
     */
    public RemoveLowerCommand(LabWorkCollection labWorkCollection, Validator validator) {
        this.labWorkCollection = labWorkCollection;
        this.validator = validator;
    }

    /**
     * Удаляет из коллекции все элементы, которые меньше заданного.
     *
     * @param args Аргументы команды, не используются.
     * @param inputSupplier Поставщик пользовательского ввода для чтения нового элемента.
     * @return Сообщение о количестве удаленных элементов.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        LabWorkReader labWorkReader = new LabWorkReader(validator);
        LabWork newLabWork = labWorkReader.readLabWork(LabWorkReader.generateId(), inputSupplier);

        int removedCount = labWorkCollection.removeIf(labWork ->
                labWork.getMinimalPoint() != null &&
                        newLabWork.getMinimalPoint() != null &&
                        labWork.getMinimalPoint() < newLabWork.getMinimalPoint()
        );

        return Messages.REMOVED + removedCount + Messages.ELEMENTS_LOWER;
    }
}
