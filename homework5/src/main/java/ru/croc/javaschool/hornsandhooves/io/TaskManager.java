package ru.croc.javaschool.hornsandhooves.io;


import ru.croc.javaschool.hornsandhooves.model.Task;
import ru.croc.javaschool.hornsandhooves.exceptions.TaskNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Мендержер задач.
 */
public class TaskManager {
    /**
     * Выполняет чтение и запись в файл.
     */
    private final TaskIO taskIO = new TaskIO();
    /**
     * Файл.
     */
    private final File file;

    /**
     * Создаёт {@link TaskManager}.
     *
     * @param file файл
     */
    public TaskManager(File file) {
        this.file = file;
    }

    /**
     * Добавление задачи в файл.
     *
     * @param task задача
     * @return true, если задача успешно записана, false иначе
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файл попал другой объект
     */
    public boolean addTask(Task task) throws IOException, ClassNotFoundException {
        List<Task> tasks = taskIO.readAllTasks(file);
        taskIO.clear(file);
        tasks.add(task);
        return taskIO.writeTasks(file, tasks);
    }

    /**
     * Редактирование задачи и перезапись его в файл.
     *
     * @param editedTask измененная задача
     * @param id         идентификатор заменяемой задачи
     * @return true, если редактирование прошло успешно, false иначе
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файл попал другой объект
     * @throws TaskNotFoundException  задача не найдена
     */
    public boolean editTask(Task editedTask, UUID id) throws IOException, ClassNotFoundException, TaskNotFoundException {
        List<Task> tasks = taskIO.readAllTasks(file);
        taskIO.clear(file);
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                tasks.set(tasks.indexOf(task), editedTask);
                if (!taskIO.clear(file)) {
                    return false;
                }
                return taskIO.writeTasks(file, tasks);
            }
        }
        throw new TaskNotFoundException();
    }

    /**
     * Удаление задачи из файла.
     *
     * @param taskId идентификатор задачи
     * @return true, если задача удалена, false иначе
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файл попал другой объект
     * @throws TaskNotFoundException  задача не найдена
     */
    public boolean deleteTask(UUID taskId) throws IOException, ClassNotFoundException, TaskNotFoundException {
        List<Task> tasks = taskIO.readAllTasks(file);
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                tasks.remove(task);
                if (!taskIO.clear(file)) {
                    return false;
                }
                return taskIO.writeTasks(file, tasks);
            }
        }
        throw new TaskNotFoundException();
    }

    /**
     * Вывод всех задач из файла. Используется десериализация.
     *
     * @return задачи из файла
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файл попал другой объект
     */
    public List<Task> readAllTasks() throws IOException, ClassNotFoundException {
        return taskIO.readAllTasks(file);
    }

    /**
     * Поиск задачи по идентификатору.
     *
     * @param id идентификатор
     * @return true, если найдена, false иначе
     * @throws TaskNotFoundException  задача не найдена
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файл попал другой объект
     */
    public Task findById(UUID id) throws TaskNotFoundException, IOException, ClassNotFoundException {
        return taskIO.findById(file, id);
    }

    /**
     * Завершает выполнение программы.
     * Создавался на случай удаления файла по завершении работы.
     *
     * @return true, если программа завершена
     * @throws IOException файл не найден.
     */
    public boolean exit() throws IOException {
        file.deleteOnExit();
        return taskIO.clear(file);
    }
}

