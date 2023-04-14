package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.model.Task;
import ru.croc.javaschool.hornsandhooves.io.TaskManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * Создание и добавление задачи.
 */
public class AddTask extends Command {

    public AddTask(String code, String information) {
        super(code, information);
    }

    @Override
    public boolean apply(TaskManager taskManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите наименование задачи: ");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите исполнителя задачи: ");
        String executor = scanner.nextLine();
        System.out.println("Введите статус задачи: ");
        String status = scanner.nextLine();
        try {
            return taskManager.addTask(new Task(name, description, executor, status));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
