package ru.croc.javaschool.finalhomework.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.expected.AccidentsLists;
import ru.croc.javaschool.finalhomework.model.entity.AccidentOut;

import javax.sql.DataSource;
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
    private DataSource dataSource;
    /**
     * Набор списков для тестов.
     */
    private final AccidentsLists accidentsLists = new AccidentsLists();

    /**
     * Инициализация источника и репозитория.
     */
    @BeforeEach
    void setUp() {
        dataSource = createTestDataSource();
        repository = new DerbyAccidentDatabaseRepository(dataSource);
        repository.deleteAll();
        repository.createMany(accidentsLists.getInitialAccidents());
    }
    /**
     * Создает источник данных для тестирования.
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


    @Test
    void createManyAndFindAllTest() {
        repository.deleteAll();
        List<AccidentOut> initialAccidents = accidentsLists.getInitialAccidents();
        repository.createMany(initialAccidents);
        Assertions.assertEquals(accidentsLists.getExpectedFindAll(), repository.findAll());
    }

    @Test
    void createTest(){
        AccidentOut accident = new AccidentOut();
        accident.setTimestamp(LocalDateTime.of(2023, 5, 15, 23, 59, 0));
        accident.setCoefficientWorkload(0.02);
        accident.setInfo("Сбили 2 пешеходов");
        repository.create(accident);
        Assertions.assertTrue(repository.findAll().contains(accident));
        Assertions.assertEquals(accidentsLists.getExpectedCreate(), repository.findAll());
    }
}
