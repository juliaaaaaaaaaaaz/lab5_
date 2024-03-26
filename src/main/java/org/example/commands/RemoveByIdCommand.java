package org.example.commands;

import org.example.commands.types.CommandWithArgs;
import org.example.core.LabWorkCollection;
import org.example.data.Messages;

import java.util.function.Supplier;

/**
 * Команда для удаления элемента из коллекции по его ID.
 */
public class RemoveByIdCommand implements CommandWithArgs {

    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды RemoveById.
     *
     * @param labWorkCollection Коллекция, из которой удаляется элемент.
     */
    public RemoveByIdCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Удаляет элемент из коллекции по указанному ID.
     *
     * @param args Аргументы команды, содержащие ID элемента для удаления.
     * @param inputSupplier Поставщик дополнительного пользовательского ввода.
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        try {
            long id = Long.parseLong(args);
            boolean removed = labWorkCollection.removeById(id);
            return removed ? Messages.REMOVE_SUCCESS : Messages.NO_LAB_WORK_FOUND_WITH_THE_PROVIDED_ID;
        } catch (NumberFormatException e) {
            return Messages.INVALID_ID_FORMAT;
        }
    }
}
