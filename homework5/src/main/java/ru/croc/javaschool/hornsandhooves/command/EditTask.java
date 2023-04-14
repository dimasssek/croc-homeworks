package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.exceptions.TaskNotFoundException;
import ru.croc.javaschool.hornsandhooves.io.TaskManager;
import ru.croc.javaschool.hornsandhooves.menu.Menu;
import ru.croc.javaschool.hornsandhooves.model.Task;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * Редактирование задачи.
 */
public class EditTask extends Command {

    /**
     * Создаёт {@link Command}
     *
     * @param code        код
     * @param information описание
     */
    public EditTask(String code, String information) {
        super(code, information);
    }

    @Override
    public boolean apply(TaskManager taskManager) throws TaskNotFoundException {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id задачи для редактирования: ");
        UUID id;

        try {
            id = UUID.fromString(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        System.out.println(menu.createEditMenu());
        while (true) {
            Task task;
            try {
                task = taskManager.findById(id);
            } catch (IOException | ClassNotFoundException | TaskNotFoundException e) {
                System.out.println(e.getMessage() + "\n");
                return false;
            }
            String choice = scanner.next();
            Boolean apply = null;
            switch (choice) {

                case "1": {
                    System.out.println("Введите новое название:");
                    task.setName(scanner.next());
                    try {
                        apply = taskManager.editTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }

                case "2": {
                    System.out.println("Введите новое описание:");
                    task.setDescription(scanner.next());
                    try {
                        apply = taskManager.editTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }

                case "3": {
                    System.out.println("Введите нового исполнителя:");
                    task.setExecutor(scanner.next());
                    try {
                        apply = taskManager.editTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }

                case "4": {
                    System.out.println("Введите новый статус:");
                    task.setStatus(scanner.next());
                    try {
                        apply = taskManager.editTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }
            }
            if (Objects.isNull(apply)) {
                System.out.println("Команда не найдена\n");
            } else
                return apply;
        }
    }
}
