package org.example.commands;

import org.example.commands.types.CommandWithoutArgs;
import org.example.core.LabWorkCollection;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Команда для группировки элементов коллекции по значению поля дисциплины и вывода количества элементов в каждой группе.
 */
public class GroupCountingByDisciplineCommand implements CommandWithoutArgs {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды GroupCountingByDiscipline.
     *
     * @param labWorkCollection Коллекция, над которой проводится операция.
     */
    public GroupCountingByDisciplineCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет группировку и выводит результат.
     *
     * @return Строка с результатами группировки.
     */
    @Override
    public String execute() {
        Map<String, Long> groupedByDiscipline = labWorkCollection.getLabWorks().stream()
                .collect(Collectors.groupingBy(labWork -> labWork.getDiscipline().getName(), Collectors.counting()));

        return groupedByDiscipline.entrySet().stream()
                .map(entry -> String.format("%s: %d", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
