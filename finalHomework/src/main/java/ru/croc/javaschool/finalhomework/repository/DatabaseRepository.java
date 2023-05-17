package ru.croc.javaschool.finalhomework.repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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
    T findByTime(LocalDateTime time) throws SQLException;

    /**
     * Возвращает все объекты из базы.
     * @return список объектов
     */
    List<T> findAll() throws SQLException;

    /**
     * Добавляет в базу новый объект.
     */
    void create(T object) throws SQLException;

    /**
     * Добавляет в базу список объектов.
     * @param objects список объектов
     */
    void createMany(List<T> objects) throws SQLException;

    /**
     * Очищает базу данных.
     */
    void deleteAll() throws SQLException;
}
