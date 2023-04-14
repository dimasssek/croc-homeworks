package ru.croc.javaschool.hornsandhooves.command;

import ru.croc.javaschool.hornsandhooves.io.TaskManager;

/**
 * Выход из программы.
 */
public class Exit extends Command{
    /**
     * Создаёт {@link Command}
     *
     * @param code        код
     * @param information описание
     */
    public Exit(String code, String information) {
        super(code, information);
    }

    @Override
    public boolean apply(TaskManager taskManager) {
        System.out.println("Работа с дазой банных завершена.");
        // taskManager.exit(); Если требуется удалить файл по завершении работы
        System.exit(0);
        return true;
    }
}
