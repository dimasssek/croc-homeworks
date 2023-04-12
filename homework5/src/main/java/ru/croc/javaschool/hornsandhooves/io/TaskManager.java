package ru.croc.javaschool.hornsandhooves.io;

import ru.croc.javaschool.hornsandhooves.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Мендержер задач.
 */
public class TaskManager {
    /**
     * Выполняет чтение и запись в файл.
     */
    private TaskIO taskIO = new TaskIO();
    /**
     * Добавление задачи в список.
     */
    public boolean add(Task task) {
        List<Task> tasks = new ArrayList<>();
    }

    /**
     * Редактирование задачи.
     */

    public boolean update() {

    }

    /**
     * Удаление задачи.
     */
    public boolean delete() {

    }

}
