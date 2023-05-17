import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.data.db.DerbyDataSourceProvider;
import ru.croc.javaschool.finalhomework.expected.AccidentsLists;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;
import ru.croc.javaschool.finalhomework.repository.DerbyAccidentDatabaseRepository;
import ru.croc.javaschool.finalhomework.service.AccidentService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;


/**
 * Демонстрационный сценарий.
 */
public class DemoSceneTest {
    /**
     * Передаёт в демо-функцию файлы и проверяет отрабатывает ли исполняемый файл.
     * @throws IOException
     */
    @Test
    void demoTest() throws IOException, SQLException {
        var lists = new AccidentsLists();
        Path trafficPath = Paths.get("src/test/resources/traffic.xml");
        Path accidentPath = Paths.get("src/test/resources/accidents.xml");
        var propertyContainer = new PropertyContainer("app.properties");
        propertyContainer.loadProperties();
        Assertions.assertNotEquals("accident_test_db", propertyContainer.getProperty("database.name"));
        Assertions.assertEquals("accidents_db", propertyContainer.getProperty("database.name"));

        var dataProvider = new DerbyDataSourceProvider(propertyContainer);
        Assertions.assertNotNull(dataProvider);

        var repository = new DerbyAccidentDatabaseRepository(dataProvider.getDataSource());
        var service = new AccidentService(repository);
        service.addAccidentsToDatabase(trafficPath, accidentPath);
        // System.out.println("Все ДТП из БД: ");
        Assertions.assertEquals(lists.getInitialAccidentsService(), service.findAll());
        service.findAll().forEach(accidentOut -> System.out.println(accidentOut.toString()));

        // System.out.println("Поиск ДТП в 13.05.2023 06:00:00");
        Assertions.assertFalse(service.findByTime(
                LocalDateTime.of(2023, 5, 13, 6, 0, 0)));

        // System.out.println("Поиск ДТП в 13.05.2023 07:00:00");
        Assertions.assertTrue(service.findByTime(
                LocalDateTime.of(2023, 5, 13, 7, 0, 0)));

        // System.out.println("Сносим БД :)");
        service.deleteAll();
        Assertions.assertEquals(lists.getEmptyList(),service.findAll());
    }
}
