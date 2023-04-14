package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.exceptions.TaskNotFoundException;
import ru.croc.javaschool.hornsandhooves.io.TaskManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Удаление задач(и).
 */
public class DeleteTask extends Command {

    public DeleteTask(String code, String information) {
        super(code, information);
    }

    @Override
    public boolean apply(TaskManager taskManager) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id задачи для удаления: ");
        try {
            var id = UUID.fromString(scanner.next());
            return taskManager.deleteTask(id);
        } catch (IllegalArgumentException | TaskNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
