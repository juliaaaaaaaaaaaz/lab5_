package org.example.di;

import org.example.commands.*;
import org.example.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Контейнер зависимостей, обеспечивающий инъекцию зависимостей в приложении.
 * Хранит экземпляры сервисов и команд, предоставляя доступ к ним по запросу.
 */
public class DIContainer {
    private final Map<Class<?>, Object> services = new HashMap<>();
    private final Map<String, Object> commands = new HashMap<>();

    /**
     * Конструктор контейнера. Инициализирует сервисы и команды приложения.
     */
    public DIContainer() {
        initializeServices();
        initializeCommands();
    }

    /**
     * Инициализирует сервисы приложения, создавая и регистрируя их экземпляры.
     */
    private void initializeServices() {
        services.put(Validator.class, new Validator());
        services.put(Printer.class, new ConsolePrinter());

        // Assuming LabWorkCollection requires a fileName
        String fileName = System.getenv("LAB_WORK_FILE");
        fileName = fileName != null && !fileName.isEmpty() ? fileName : "collection.json";
        LabWorkCollection labWorkCollection = new LabWorkCollection(fileName);

        // Corrected: Instantiate Validator properly
        Validator validator = new Validator();

        services.put(LabWorkCollection.class, labWorkCollection);

        // Using services to create a CommandExecutor
        CommandExecutor commandExecutor = new CommandExecutor(this);
        services.put(CommandExecutor.class, commandExecutor);

        services.put(CommandParser.class, new CommandParser(commandExecutor));
        services.put(LabWorkReader.class, new LabWorkReader(validator));
    }

    /**
     * Инициализирует команды приложения, создавая и регистрируя их экземпляры.
     */
    private void initializeCommands() {
        commands.put("add", new AddCommand(getService(LabWorkCollection.class), getService(LabWorkReader.class)));
        commands.put("clear", new ClearCommand(getService(LabWorkCollection.class)));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("exit", new ExitCommand());
        commands.put("group_counting_by_discipline", new GroupCountingByDisciplineCommand(getService(LabWorkCollection.class)));
        commands.put("help", new HelpCommand(this));
        commands.put("info", new InfoCommand(getService(LabWorkCollection.class)));
        commands.put("print_ascending", new PrintAscendingCommand(getService(LabWorkCollection.class)));
        commands.put("print_field_descending_discipline", new PrintFieldDescendingDisciplineCommand(getService(LabWorkCollection.class)));
        commands.put("remove_by_id", new RemoveByIdCommand(getService(LabWorkCollection.class)));
        commands.put("remove_greater", new RemoveGreaterCommand(getService(LabWorkCollection.class), getService(LabWorkReader.class)));
        commands.put("remove_lower", new RemoveLowerCommand(getService(LabWorkCollection.class), getService(Validator.class)));
        commands.put("save", new SaveCommand(getService(LabWorkCollection.class)));
        commands.put("show", new ShowCommand(getService(LabWorkCollection.class)));
        commands.put("update", new UpdateCommand(getService(LabWorkCollection.class), getService(Validator.class)));
        commands.put("add_if_min", new AddIfMinCommand(getService(LabWorkCollection.class), getService(LabWorkReader.class)));

    }

    /**
     * Возвращает экземпляр сервиса по его классу.
     *
     * @param <T> Тип сервиса.
     * @param clazz Класс сервиса.
     * @return Экземпляр сервиса.
     */
    public <T> T getService(Class<T> clazz) {
        return clazz.cast(services.get(clazz));
    }

    /**
     * Возвращает экземпляр команды по ее имени.
     *
     * @param commandName Имя команды.
     * @return Экземпляр команды.
     */
    public Object getCommand(String commandName) {
        return commands.get(commandName);
    }

    /**
     * Возвращает набор имен доступных команд.
     *
     * @return Набор имен команд.
     */
    public Set<String> getCommandNames() {
        return commands.keySet();
    }
}
