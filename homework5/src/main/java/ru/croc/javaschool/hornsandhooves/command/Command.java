package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.io.TaskManager;

/**
 * Исполняемая команда.
 */
public abstract class Command {
    /**
     * Код.
     */
    private final String code;
    /**
     * Описание.
     */
    private final String information;

    /**
     * Создаёт {@link Command}
     * @param code код
     * @param information описание
     */
    public Command(String code, String information) {
        this.code = code;
        this.information = information;
    }

    /**
     * Выполнение команды.
     */
    public abstract void apply(TaskManager taskManager);

    public String getCode() {
        return code;
    }

    public String getInformation() {
        return information;
    }
}
