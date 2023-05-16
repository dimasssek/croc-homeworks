package ru.croc.javaschool.finalhomework.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Необходимо реализовать операции чтения и записи.
 * @param <T>
 */
public interface DatabaseRepository<T> {
    /**
     * Поиск по временной точке.
     * @param time временная точка
     * @return объект
     */
    T findByTime(LocalDateTime time);

    /**
     * Возвращает все объекты из базы.
     * @return список объектов
     */
    List<T> findAll();

    /**
     * Добавляет в базу новый объект.
     * @return новый объект
     */
    void create(T object);

    /**
     * Добавляет в базу список объектов.
     * @param objects список объектов
     * @return добавленный список объектов
     */
    void createMany(List<T> objects);

    /**
     * Очищает базу данных.
     */
    void deleteAll();
}
