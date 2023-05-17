package ru.croc.javaschool.finalhomework.repository;

import ru.croc.javaschool.finalhomework.model.AccidentOut;

/**
 * Определяет поведения репозитория для БД {@link AccidentOut}.
 */
public interface AccidentDatabaseRepository extends DatabaseRepository<AccidentOut> {
    /**
     * Название таблицы.
     */
    String TABLE_NAME = "accidents";
}
