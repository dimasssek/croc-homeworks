package ru.croc.javaschool.hornsandhooves.io;

import ru.croc.javaschool.hornsandhooves.model.Task;
import ru.croc.javaschool.hornsandhooves.exceptions.TaskNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс для выполнения чтения и записи в файл.
 */
public class TaskIO {
    /**
     * Чтение всех задач из файла.
     *
     * @param file файл
     * @return список всех задач из файла
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файле другой объект
     */
    public List<Task> readAllTasks(File file) throws IOException, ClassNotFoundException {
        List<Task> tasks = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                tasks.add((Task) objectInputStream.readObject());
            }
        } catch (EOFException e) {}
        return tasks;
    }

    /**
     * Нахождение задачи по идентификатору.
     *
     * @param file файл
     * @param id   идентификатор
     * @return найденная задача или null, если задача не найдена
     * @throws IOException            файл не найден
     * @throws ClassNotFoundException в файле другой объект
     * @throws TaskNotFoundException  задача не найдена в файле
     */
    public Task findById(File file, UUID id) throws IOException, TaskNotFoundException, ClassNotFoundException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                Task task = (Task) objectInputStream.readObject();
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        } catch (EOFException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Вставка списка задач в заданный файл.
     *
     * @param file  файл
     * @param tasks список задач
     * @return true, если файл найден и все задачи вставлены
     * @throws IOException файл не найден
     */
    public boolean writeTasks(File file, List<Task> tasks) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        for (Task task : tasks) {
            outputStream.writeObject(task);
        }
        outputStream.close();
        return true;
    }

    /**
     * Очищение потока ввода.
     *
     * @param file файл
     * @return true, если найдено и очищено
     * @throws IOException файл не найден
     */
    public boolean clear(File file) throws IOException {
        new FileOutputStream(file).close();
        return true;
    }
}



