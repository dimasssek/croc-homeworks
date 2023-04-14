package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.model.Task;
import ru.croc.javaschool.hornsandhooves.io.TaskManager;

import java.io.IOException;
import java.util.List;

/**
 * Выводит на экран все задачи.
 */
public class ShowTask extends Command {
    /**
     * Создаёт {@link Command}
     *
     * @param code        код
     * @param information описание
     */
    public ShowTask(String code, String information) {
        super(code, information);
    }

    @Override
    public boolean apply(TaskManager taskManager) {
        List<Task> tasks;
        try {
            tasks = taskManager.readAllTasks();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        if (tasks == null) {
            return false;
        }
        for (Task task : tasks) {
            System.out.println(task + "\n");
        }
        return true;
    }
}
