package ru.croc.javaschool.finalhomework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.data.db.DerbyDataSourceProvider;
import ru.croc.javaschool.finalhomework.expected.AccidentsLists;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;
import ru.croc.javaschool.finalhomework.repository.AccidentDatabaseRepository;
import ru.croc.javaschool.finalhomework.repository.DerbyAccidentDatabaseRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
    private DerbyDataSourceProvider dataSource;
    /**
     * Набор списков для тестов.
     */
    private final AccidentsLists accidentsLists = new AccidentsLists();
    /**
     * Настройки.
     */
    private final PropertyContainer propertyContainer = new PropertyContainer("app_test.properties");

    /**
     * Создает тестовый источник данных и репозиторий.
     */
    @BeforeEach
    void setUp() throws IOException, SQLException {
        dataSource = createTestDataProvider();
        repository = new DerbyAccidentDatabaseRepository(dataSource.getDataSource());
        service = new AccidentService(repository);
        repository.deleteAll();
    }

    /**
     * Создаёт источник данных.
     *
     * @return источник данных
     * @throws IOException
     */
    private DerbyDataSourceProvider createTestDataProvider() throws IOException {
        propertyContainer.loadProperties();
        return new DerbyDataSourceProvider(propertyContainer);
    }

    /**
     * Проверка заполнения из xml.
     *
     * @throws IOException если проблемы с файлами
     */
    @Test
    public void addAccidentsToDatabaseTest() throws IOException, SQLException {
        repository.deleteAll();
        Path trafficXml = Paths.get("src/test/resources/traffic.xml");
        Path accidentXml = Paths.get("src/test/resources/accidents.xml");
        service.addAccidentsToDatabase(trafficXml, accidentXml);
        Assertions.assertEquals(accidentsLists.getInitialAccidentsService(), service.findAll());
        Assertions.assertFalse(repository.findAll().isEmpty());
    }

    /**
     * Поиск всех заполненных записей.
     */
    @Test
    public void findAllTest() throws SQLException {
        repository.createMany(accidentsLists.getInitialAccidentsService());
        Assertions.assertEquals(accidentsLists.getInitialAccidentsService(), service.findAll());
    }

    /**
     * Тест для {@link AccidentService#findByTime(LocalDateTime)}.
     */
    @Test
    public void findByTime() throws SQLException {
        repository.createMany(accidentsLists.getInitialAccidentsService());
        Assertions.assertTrue(
                service.findByTime(LocalDateTime.of(2023, 5, 13, 7, 0, 0)));
    }
}
