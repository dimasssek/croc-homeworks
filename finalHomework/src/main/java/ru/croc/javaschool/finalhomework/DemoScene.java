package ru.croc.javaschool.finalhomework;

import ru.croc.javaschool.finalhomework.data.db.DerbyDataSourceProvider;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;
import ru.croc.javaschool.finalhomework.repository.DerbyAccidentDatabaseRepository;
import ru.croc.javaschool.finalhomework.service.AccidentService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Демонстрационный сценарий.
 */
public class DemoScene {

    public static boolean demo(Path pathTraffic, Path pathAccident) {
        try {
            PropertyContainer.loadProperties();
            var dataSourceProvider = new DerbyDataSourceProvider();
            var repository = new DerbyAccidentDatabaseRepository(dataSourceProvider.getDataSource());
            var service = new AccidentService(repository);
            service.addAccidentsToDatabase(pathTraffic, pathAccident);
            System.out.println("Все ДТП из БД: ");
            service.findAll().forEach(accidentOut -> System.out.println(accidentOut.toString()));
            System.out.println("Поиск ДТП в 13.05.2023 07:00:00");
            service.findByTime(LocalDateTime.of(2023, 5, 13, 7, 0, 0));
            System.out.println("Сносим БД :)");
            service.deleteAll();
            service.findAll();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

//    public static void main(String[] args) {
//        Path trafficPath = Paths.get("src/test/resources/traffic.xml");
//        Path accidentPath = Paths.get("src/test/resources/accidents.xml");
//        demo(trafficPath, accidentPath);
//    }
}
