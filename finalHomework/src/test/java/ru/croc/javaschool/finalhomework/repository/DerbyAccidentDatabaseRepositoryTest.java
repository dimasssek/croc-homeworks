package ru.croc.javaschool.finalhomework.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.data.db.DerbyDataSourceProvider;
import ru.croc.javaschool.finalhomework.expected.AccidentsLists;
import ru.croc.javaschool.finalhomework.model.AccidentOut;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Тест для {@link DerbyAccidentDatabaseRepository}.
 */
public class DerbyAccidentDatabaseRepositoryTest {

    /**
     * Репозиторий ДТП.
     */
    private AccidentDatabaseRepository repository;
    /**
     * Источник данных.
     */
    private DerbyDataSourceProvider dataProvider;
    /**
     * Набор списков для тестов.
     */
    private final AccidentsLists accidentsLists = new AccidentsLists();
    /**
     * Настройки.
     */
    private final PropertyContainer propertyContainer = new PropertyContainer("app_test.properties");

    /**
     * Инициализация источника и репозитория.
     */
    @BeforeEach
    void setUp() throws IOException, SQLException {
        dataProvider = createTestDataProvider();
        repository = new DerbyAccidentDatabaseRepository(dataProvider.getDataSource());
        repository.deleteAll();
        repository.createMany(accidentsLists.getInitialAccidents());
    }

    /**
     * Создаёт источник данных.
     * @return источник данных
     * @throws IOException
     */
    private DerbyDataSourceProvider createTestDataProvider() throws IOException {
        propertyContainer.loadProperties();
        return new DerbyDataSourceProvider(propertyContainer);
    }

    /**
     * Тест для {@link DerbyAccidentDatabaseRepository#createMany(List<AccidentOut>)} и
     * {@link DerbyAccidentDatabaseRepository#findAll()}.
     */
    @Test
    void createManyAndFindAllTest() throws SQLException {
        Assertions.assertEquals(accidentsLists.getExpectedFindAll(), repository.findAll());
    }

    /**
     * Тест для {@link DerbyAccidentDatabaseRepository#create(AccidentOut)}.
     */
    @Test
    void createTest() throws SQLException {
        AccidentOut accident = new AccidentOut();
        accident.setTimestamp(LocalDateTime.of(2023, 5, 15, 23, 59, 0));
        accident.setCoefficientWorkload(new BigDecimal("0.02"));
        accident.setInfo("Сбили 2 пешеходов");
        repository.create(accident);
        Assertions.assertTrue(repository.findAll().contains(accident));
        Assertions.assertEquals(accidentsLists.getExpectedCreate(), repository.findAll());
    }
}
