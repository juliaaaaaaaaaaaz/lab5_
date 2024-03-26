package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.core.LabWorkCollection;
import org.example.core.LabWorkReader;
import org.example.core.Validator;
import org.example.data.LabWork;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для обновления элемента коллекции по его ID.
 */
public class UpdateCommand implements CommandWithArgs {
    private final LabWorkCollection labWorkCollection;
    private final Validator validator;

    /**
     * Конструктор команды Update.
     *
     * @param labWorkCollection Коллекция, в которой производится обновление.
     * @param validator Валидатор для проверки вводимых данных.
     */
    public UpdateCommand(LabWorkCollection labWorkCollection, Validator validator) {
        this.labWorkCollection = labWorkCollection;
        this.validator = validator;
    }

    /**
     * Обновляет элемент коллекции с указанным ID.
     *
     * @param args Аргументы команды, содержащие ID элемента для обновления.
     * @param inputSupplier Поставщик пользовательского ввода для чтения нового состояния элемента.
     * @return Сообщение о результате обновления элемента.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        try {
            long id = Long.parseLong(args.split("\\s+")[0]);
            LabWorkReader labWorkReader = new LabWorkReader(validator);
            LabWork updatedLabWork = labWorkReader.readLabWork(id, inputSupplier);
            boolean updated = labWorkCollection.update(id, updatedLabWork);

            return updated ? Messages.LAB_WORK_WITH_ID + id + Messages.UPDATE_SUCCESS : Messages.UPDATE_FAILED + id;
        } catch (Exception e) {
            return Messages.ERROR_UPDATING_LABWORK + e.getMessage();
        }
    }
}
