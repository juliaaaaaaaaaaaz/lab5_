package org.example.core;

import org.example.data.*;
import org.example.exceptions.ValidationException;
import org.example.utils.IdGenerator;

import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

/**
 * Класс для чтения данных объектов LabWork из пользовательского ввода.
 */
public class LabWorkReader {
    private final Validator validator;

    /**
     * Конструктор класса LabWorkReader.
     *
     * @param validator Валидатор для проверки введенных данных.
     */
    public LabWorkReader(Validator validator) {
        this.validator = validator;
    }

    /**
     * Генерирует уникальный ID для объекта LabWork.
     *
     * @return Уникальный ID.
     */
    public static Long generateId() {
        return IdGenerator.generateUniqueId();
    }

    private String readName(Supplier<String> inputSupplier) {
        while (true) {
            System.out.print(Messages.ENTER_NAME);
            String name = inputSupplier.get();
            try {
                validator.validateName(name);
                return name;
            } catch (ValidationException e) {
                System.out.println(Messages.INVALID_NAME);
            }
        }
    }


    private Coordinates readCoordinates(Supplier<String> inputSupplier) {
        while (true) {
            try {
                System.out.print(Messages.ENTER_X);
                float x = Float.parseFloat(inputSupplier.get().trim());
                System.out.print(Messages.ENTER_Y);
                double y = Double.parseDouble(inputSupplier.get().trim());
                Coordinates coordinates = new Coordinates();
                coordinates.setX(x);
                coordinates.setY(y);
                validator.validateCoordinates(coordinates);
                return coordinates;
            } catch (NumberFormatException | ValidationException e) {
                System.out.println(Messages.INVALID_COORDINATES);
            }
        }
    }

    private long readMaximumPoint(Supplier<String> inputSupplier) {
        while (true) {
            try {
                System.out.print(Messages.ENTER_MAX_POINT);
                long maxPoint = Long.parseLong(inputSupplier.get().trim());
                validator.validateMaximumPoint(maxPoint);
                return maxPoint;
            } catch (NumberFormatException | ValidationException e) {
                System.out.println(Messages.INVALID_MAX_POINT);

            }
        }
    }

    private Difficulty readDifficulty(Supplier<String> inputSupplier) {
        System.out.print(Messages.ENTER_DIFFICULTY);
        while (true) {
            String input = inputSupplier.get().trim();
            try {
                Difficulty difficulty = Difficulty.valueOf(input.toUpperCase(Locale.getDefault()));
                validator.validateDifficulty(difficulty);
                return difficulty;
            } catch (IllegalArgumentException | ValidationException e) {
                System.out.println(Messages.INVALID_DIFFICULTY);
            }
        }
    }


    private int readPracticeHours(Supplier<String> inputSupplier) {
        System.out.print(Messages.ENTER_PRACTICE_HOURS);
        while (true) {
            try {
                return Integer.parseInt(inputSupplier.get().trim());
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_PRACTICE_HOURS);
            }
        }
    }

    private Discipline readDiscipline(Supplier<String> inputSupplier) {
        while (true) {
            try {
                System.out.print(Messages.ENTER_DISCIPLINE);
                String name = inputSupplier.get().trim();
                int practiceHours = readPracticeHours(inputSupplier);
                int selfStudyHours = (int) readSelfStudyHours(inputSupplier);
                Discipline discipline = new Discipline();
                discipline.setName(name);
                discipline.setPracticeHours(practiceHours);
                discipline.setSelfStudyHours(selfStudyHours);
                validator.validateDiscipline(discipline);
                return discipline;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private long readSelfStudyHours(Supplier<String> inputSupplier) {
        while (true) {
            try {
                System.out.print(Messages.ENTER_SELF_STUDY_HOURS);
                long value = Long.parseLong(inputSupplier.get());
                validator.validateSelfStudyHours(value);
                return value;
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private long readMinimalPoint(Supplier<String> inputSupplier) {
        System.out.print(Messages.ENTER_MINIMAL_POINT);
        while (true) {
            try {
                long point = Long.parseLong(inputSupplier.get().trim());
                validator.validateMinimalPoint(point);
                return point;
            } catch (NumberFormatException | ValidationException e) {
                System.out.println(Messages.INVALID_MINIMAL_POINT);
            }
        }
    }

    /**
     * Читает и возвращает объект LabWork, созданный из пользовательского ввода.
     *
     * @param id ID для нового объекта LabWork.
     * @return Новый объект LabWork.
     */
    public LabWork readLabWork(long id, Supplier<String> inputSupplier) {
        String name = readName(inputSupplier);
        Coordinates coordinates = readCoordinates(inputSupplier);
        Date creationDate = new Date();
        Long minimalPoint = readMinimalPoint(inputSupplier);
        long maximumPoint = readMaximumPoint(inputSupplier);
        Difficulty difficulty = readDifficulty(inputSupplier);
        Discipline discipline = readDiscipline(inputSupplier);
        return new LabWork(id, name, coordinates, creationDate, minimalPoint, maximumPoint, difficulty, discipline);
    }
}
