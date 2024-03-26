package org.example.commands;

import org.example.core.LabWorkCollection;
import org.example.core.LabWorkReader;
import org.example.data.LabWork;
import org.example.commands.types.CommandWithArgs;
import org.example.data.Messages;

import java.util.Comparator;
import java.util.function.Supplier;
/**
 * Команда для добавления нового элемента в коллекцию, если его значение меньше, чем у минимального элемента в коллекции.
 */
public class AddIfMinCommand implements CommandWithArgs {
    private final LabWorkCollection labWorkCollection;
    private final LabWorkReader labWorkReader;

    /**
     * Конструктор команды AddIfMin.
     *
     * @param labWorkCollection Коллекция, куда может быть добавлен новый элемент.
     * @param labWorkReader Считыватель данных LabWork.
     */
    public AddIfMinCommand(LabWorkCollection labWorkCollection, LabWorkReader labWorkReader) {
        this.labWorkCollection = labWorkCollection;
        this.labWorkReader = labWorkReader;
    }

    /**
     * Выполняет добавление нового элемента в коллекцию, если его значение минимально.
     *
     * @param args Аргументы команды.
     * @param inputSupplier Поставщик ввода пользователя.
     * @return Сообщение о результате выполнения команды.
     */
    @Override
    public String execute(String args, Supplier<String> inputSupplier) {
        long id = LabWorkReader.generateId();
        LabWork newLabWork = labWorkReader.readLabWork(id, inputSupplier);
        boolean isMin = labWorkCollection.getLabWorks().stream()
                .min(Comparator.comparingInt(labWork -> labWork.getDiscipline().getPracticeHours()))
                .map(minLabWork -> newLabWork.getDiscipline().getPracticeHours() < minLabWork.getDiscipline().getPracticeHours())
                .orElse(true);

        if (isMin) {
            labWorkCollection.add(newLabWork);
            return Messages.LAB_WORK_SUCCESS_ADD_IF_MIN;
        } else {
            return Messages.LAB_WORK_NOT_MIN;
        }
    }
}
