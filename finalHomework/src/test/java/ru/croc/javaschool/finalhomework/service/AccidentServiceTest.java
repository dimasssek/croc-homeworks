package ru.croc.javaschool.finalhomework.service;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.expected.AccidentsLists;
import ru.croc.javaschool.finalhomework.repository.AccidentDatabaseRepository;
import ru.croc.javaschool.finalhomework.repository.DerbyAccidentDatabaseRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Тест {@link AccidentService}.
 */
public class AccidentServiceTest {
    /**
     * Репозиторий ДТП.
     */
    private AccidentDatabaseRepository repository;
    /**
     * Сервис для ДТП.
     */
    private AccidentService service;
    /**
     * Источник данных.
     */
    private DataSource dataSource;
    /**
     * Набор списков для тестов.
     */
    private final AccidentsLists accidentsLists = new AccidentsLists();
    /**
     * Создает тестовый источник данных и репозиторий.
     */
    @BeforeEach
    void setUp() {
        dataSource = createTestDataSource();
        repository = new DerbyAccidentDatabaseRepository(dataSource);
        service = new AccidentService(repository);
        repository.deleteAll();
        repository.createMany(accidentsLists.getInitialAccidentsService());
    }
    /**
     * Создание источника данных для тестирования.
     *
     * @return источник данных
     */
    private static DataSource createTestDataSource() {
        String dbName = "accident_db_test";
        return new EmbeddedDataSource() {{
            setDatabaseName(dbName);
            setCreateDatabase("create");
        }};
    }

    /**
     * Проверка заполнения из xml.
     * @throws IOException если проблемы с файлами
     */
    @Test
    public void addAccidentsToDatabaseTest() throws IOException {
        Path trafficXml = Paths.get("src/test/resources/traffic.xml");
        Path accidentXml = Paths.get("src/test/resources/accidents.xml");
        repository.deleteAll();
        service.addAccidentsToDatabase(trafficXml, accidentXml);
        Assertions.assertFalse(repository.findAll().isEmpty());
    }

    /**
     * Поиск всех заполненных записей.
     */
    @Test
    public void findAllTest() {
        Assertions.assertEquals(accidentsLists.getInitialAccidentsService(),service.findAll());
    }
}
