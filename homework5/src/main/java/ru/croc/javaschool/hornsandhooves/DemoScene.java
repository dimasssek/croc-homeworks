package ru.croc.javaschool.hornsandhooves;

import ru.croc.javaschool.hornsandhooves.command.*;
import ru.croc.javaschool.hornsandhooves.exceptions.TaskNotFoundException;
import ru.croc.javaschool.hornsandhooves.io.TaskManager;
import ru.croc.javaschool.hornsandhooves.menu.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Демонстрационный класс.
 */
public class DemoScene {
    public static void main(String[] args) throws IOException, ClassNotFoundException, TaskNotFoundException {
        List<Command> commands = new ArrayList<>();
        commands.add(new AddTask("1", "Добавить задачу"));
        commands.add(new EditTask("2","Редактировать задачу"));
        commands.add(new DeleteTask("3", "Удалить задачу"));
        commands.add(new ShowTask("4", "Показать все задачи"));
        commands.add(new Exit("0", "Завершить работу"));
        Menu menu = new Menu();
        Boolean apply = null;
        File file = new File("db.txt");
        file.createNewFile();
        TaskManager taskManager = new TaskManager(file);
        while (true) {
            System.out.println(menu.createMainMenu(commands));
            String code = new Scanner(System.in).nextLine();
            for (Command command : commands) {
                if (command.getCode().equals(code))
                    apply = command.apply(taskManager);
            }
            if (Objects.isNull(apply)) {
                System.out.println("Команда не найдена\n");
            } else if (apply) {
                System.out.println("Команда успешно выполнена\n");
            } else {
                System.out.println("Команда не выполнена\n");
            }
        }
    }
}
