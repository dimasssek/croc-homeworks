package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.io.TaskManager;

/**
 * Создание и добавление задачи.
 */
public class AddTask extends Command {

    public AddTask(String code, String information) {
        super(code, information);
    }

    @Override
    public void apply(TaskManager taskManager) {

    }
}
