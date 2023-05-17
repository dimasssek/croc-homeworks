package ru.croc.javaschool.finalhomework.repository;

import ru.croc.javaschool.finalhomework.model.AccidentOut;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с ДТП.
 */
public class DerbyAccidentDatabaseRepository implements AccidentDatabaseRepository {
    /**
     * Источник данных.
     */
    private final DataSource dataSource;

    /**
     * Создаёт {@link DerbyAccidentDatabaseRepository} и инициализирует таблицу.
     * @param dataSource источник данных
     */
    public DerbyAccidentDatabaseRepository(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Поиск по временной точке.
     * @param time временная точка
     * @return {@link AccidentOut}, если найдено, {@code null} иначе.
     */
    @Override
    public AccidentOut findByTime(LocalDateTime time) throws SQLException {
        var accident = (AccidentOut) null;
        var sqlQuery = String.format("SELECT * FROM %s WHERE timestamp = '%s'",
                AccidentDatabaseRepository.TABLE_NAME,
                Timestamp.valueOf(time));

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sqlQuery)) {
            if (resultSet.next()) {
                accident = new AccidentOut(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getTimestamp("timestamp").toLocalDateTime(),
                        resultSet.getBigDecimal("coefficientWorkload"),
                        resultSet.getString("info")
                );
            }
        } catch (SQLException exception) {
           throw exception;
        }
        return accident;
    }

    /**
     * Возвращает все ДТП из базы.
     * @return список дтп
     */
    @Override
    public List<AccidentOut> findAll() throws SQLException {
        var sqlQuery = String.format("SELECT * FROM %s", AccidentDatabaseRepository.TABLE_NAME);
        return sampleQuery(sqlQuery);
    }

    /**
     * Добавляет в базу новое дтп.
     * @param accidentOut новое дтп
     */
    @Override
    public void create(AccidentOut accidentOut) throws SQLException {
        var sqlQuery = String.format("INSERT INTO %s VALUES (?,?,?,?)", AccidentDatabaseRepository.TABLE_NAME);
        var accidentId = UUID.randomUUID();
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(
                    1,
                    accidentId.toString());
            statement.setTimestamp(
                    2,
                    Timestamp.valueOf(accidentOut.getTimestamp())
            );
            statement.setBigDecimal(
                    3,
                    accidentOut.getCoefficientWorkload()
            );
            statement.setString(4,
                    accidentOut.getInfo());
            statement.execute();
        } catch (SQLException exception) {
           throw exception;
        }
    }

    /**
     * Добавляет в базу список дтп.
     * @param accidents список дтп
     */
    @Override
    public void createMany(List<AccidentOut> accidents) throws SQLException {
        var sqlQuery = String.format("INSERT INTO %s VALUES (?,?,?,?)", AccidentDatabaseRepository.TABLE_NAME);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            connection.setAutoCommit(false);
            for (AccidentOut accident : accidents) {
                statement.setString(
                        1,
                        UUID.randomUUID().toString());
                statement.setTimestamp(
                        2,
                        Timestamp.valueOf(accident.getTimestamp())
                );
                statement.setBigDecimal(
                        3,
                        accident.getCoefficientWorkload()
                );
                statement.setString(4,
                        accident.getInfo());
                statement.execute();
            }
            connection.commit();
        } catch (SQLException exception) {
            throw exception;
        }
    }

    /**
     * Очищает базу данных.
     */
    @Override
    public void deleteAll() throws SQLException {
        var sqlQuery = String.format("DELETE FROM %s", AccidentDatabaseRepository.TABLE_NAME);
        voidQuery(sqlQuery);
    }

    /**
     * Создание и инициализация таблицы.
     */
    private void initTable() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = connection.getMetaData().getTables(
                     null,
                     null,
                     AccidentDatabaseRepository.TABLE_NAME.toUpperCase(),
                     new String[]{"TABLE"})) {
            if (resultSet.next()) {
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + AccidentDatabaseRepository.TABLE_NAME
                                + " ("
                                + "id VARCHAR(40) PRIMARY KEY, "
                                + "timestamp TIMESTAMP NOT NULL, "
                                + "coefficientWorkload DOUBLE, "
                                + "info VARCHAR(50) NOT NULL"
                                + ")");
            }
        } catch (SQLException exception) {
            throw exception;
        }
    }

    /**
     * Возвращает результат выполнения SQL запроса. Используется c SELECT в запросе.
     *
     * @param sqlQuery SQL запрос
     * @return результат запроса
     */
    private List<AccidentOut> sampleQuery(String sqlQuery) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery);
             var resultSet = statement.executeQuery()) {
            var accidents = new ArrayList<AccidentOut>();
            while (resultSet.next()) {
                accidents.add(new AccidentOut(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getTimestamp("timestamp").toLocalDateTime(),
                        resultSet.getBigDecimal("coefficientWorkload"),
                        resultSet.getString("info")
                ));
            }
            return accidents;
        } catch (SQLException exception) {
            throw exception;
        }
    }

    /**
     * Выполняет запрос SQL, результат выполнения которого ничего не возвращает.
     *
     * @param sqlQuery SQL запрос
     */
    private void voidQuery(String sqlQuery) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (Exception exception) {
           throw exception;
        }
    }
}
