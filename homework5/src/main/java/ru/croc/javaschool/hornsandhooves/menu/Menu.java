package ru.croc.javaschool.hornsandhooves.menu;

import ru.croc.javaschool.hornsandhooves.command.Command;

import java.util.List;

/**
 * Создание меню.
 */
public class Menu {
    /**
     * Создаёт главное меню.
     * @param commands команды для выполнения
     * @return преобразованное меню в качестве строки
     */
    public String createMainMenu(List<Command> commands) {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("Главное меню: \n");
        for (Command command: commands) {
            mainMenu.append(command.getCode()).append(". ").append(command.getInformation()).append("\n");
        }
        return mainMenu.toString();
    }

    /**
     * Создаёт меню для редактирования задачи.
     * @return меню
     */
    public String createEditMenu() {
        return "1. Изменить название\n" +
                "2. Изменить описание\n" +
                "3. Изменить исполнителя\n" +
                "4. Изменить статус\n";
    }
}
