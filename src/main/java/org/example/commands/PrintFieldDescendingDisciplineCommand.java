package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;
import org.example.data.LabWork;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Команда для вывода элементов коллекции по убыванию значения поля дисциплины.
 */
public class PrintFieldDescendingDisciplineCommand implements CommandWithoutArgs {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды PrintFieldDescendingDiscipline.
     *
     * @param labWorkCollection Коллекция, над которой проводится операция.
     */
    public PrintFieldDescendingDisciplineCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выводит элементы коллекции в порядке убывания значений поля дисциплины.
     *
     * @return Отсортированные элементы коллекции.
     */
    @Override
    public String execute() {
        return labWorkCollection.getLabWorks().stream()
                .filter(labWork -> labWork.getDiscipline() != null)
                .sorted(Comparator.comparingInt((LabWork lw) -> lw.getDiscipline().getPracticeHours()).reversed())
                .map(lw -> lw.getDiscipline().getName() + " - Practice hours: " + lw.getDiscipline().getPracticeHours())
                .collect(Collectors.joining("\n"));
    }
}
