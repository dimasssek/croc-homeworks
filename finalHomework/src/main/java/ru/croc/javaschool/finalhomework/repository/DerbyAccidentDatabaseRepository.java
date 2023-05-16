package ru.croc.javaschool.finalhomework.repository;

import ru.croc.javaschool.finalhomework.model.entity.AccidentOut;

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

    public DerbyAccidentDatabaseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    @Override
    public AccidentOut findByTime(LocalDateTime time) {
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
                        resultSet.getDouble("coefficientWorkload"),
                        resultSet.getString("info")
                );
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка с запросом (поиск по временной точке)"+e.getMessage());
        }

        return accident;
    }

    @Override
    public List<AccidentOut> findAll() {
        var sqlQuery = String.format("SELECT * FROM %s", AccidentDatabaseRepository.TABLE_NAME);
        return sampleQuery(sqlQuery);
    }

    @Override
    public void create(AccidentOut accidentOut) {
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
            statement.setDouble(
                    3,
                    accidentOut.getCoefficientWorkload()
            );
            statement.setString(4,
                    accidentOut.getInfo());
            statement.execute();
            System.out.println("Объект успешно добавлен в базу ");
        } catch (SQLException exception) {
            System.out.println("Возникла ошибка при заполнении таблицы " + exception.getMessage());
        } finally {
            System.out.println("=======================================================");
        }
    }

    @Override
    public void createMany(List<AccidentOut> accidents) {
        for (AccidentOut accident : accidents) {
            create(accident);
        }
    }

    @Override
    public void deleteAll() {
        var sqlQuery = String.format("DELETE FROM %s", AccidentDatabaseRepository.TABLE_NAME);
        voidQuery(sqlQuery);
    }

    /**
     * Создание и инициализация таблицы.
     */
    private void initTable() {
        System.out.printf("Инициализация таблицы " + AccidentDatabaseRepository.TABLE_NAME + "%n");
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetaData = connection.getMetaData();
            var resultSet = databaseMetaData.getTables(
                    null,
                    null,
                    AccidentDatabaseRepository.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Таблица уже инициализирована");
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
                System.out.println("Таблица успешно создана");
            }
        } catch (SQLException exception) {
            System.out.println("Возникла ошибка при создании таблицы " + exception.getMessage());
        } finally {
            System.out.println("=======================================================");
        }
    }

    /**
     * Возвращает результат выполнения SQL запроса. Используется c SELECT в запросе.
     *
     * @param sqlQuery SQL запрос
     * @return результат запроса
     */
    private List<AccidentOut> sampleQuery(String sqlQuery) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            var resultSet = statement.executeQuery();
            var accidents = new ArrayList<AccidentOut>();
            while (resultSet.next()) {
                accidents.add(new AccidentOut(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getTimestamp("timestamp").toLocalDateTime(),
                        resultSet.getDouble("coefficientWorkload"),
                        resultSet.getString("info")
                ));
            }
            System.out.println("Запрос выполнен успешно ");
            return accidents;
        } catch (SQLException exception) {
            System.out.println("Возникла ошибка при выполнении запроса к таблице " + exception.getMessage());
        } finally {
            System.out.println("=======================================================");
        }
        return new ArrayList<>();
    }

    /**
     * Выполняет запрос SQL, результат выполнения которого ничего не возвращает.
     *
     * @param sqlQuery SQL запрос
     */
    private void voidQuery(String sqlQuery) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
            System.out.println("Пустой запрос выполнен успешно ");
        } catch (Exception e) {
            System.out.println("Возникла ошибка при выполнении пустого запроса к таблице: " + e.getMessage());
        } finally {
            System.out.println("=======================================================");
        }
    }
}
