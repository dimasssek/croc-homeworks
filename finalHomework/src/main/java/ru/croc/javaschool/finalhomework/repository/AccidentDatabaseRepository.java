package ru.croc.javaschool.finalhomework.repository;

import ru.croc.javaschool.finalhomework.model.entity.AccidentOut;

/**
 * Определяет поведения репозитория для БД {@link AccidentOut}.
 */
public interface AccidentDatabaseRepository extends DatabaseRepository<AccidentOut> {
    String TABLE_NAME = "accidents";
}
