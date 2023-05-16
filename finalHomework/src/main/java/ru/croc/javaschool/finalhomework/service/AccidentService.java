package ru.croc.javaschool.finalhomework.service;

import ru.croc.javaschool.finalhomework.data.xml.JaxbConverter;
import ru.croc.javaschool.finalhomework.model.input.AccidentIn;
import ru.croc.javaschool.finalhomework.model.input.AccidentListIn;
import ru.croc.javaschool.finalhomework.model.input.TrafficIn;
import ru.croc.javaschool.finalhomework.model.input.TrafficListIn;
import ru.croc.javaschool.finalhomework.model.entity.AccidentOut;
import ru.croc.javaschool.finalhomework.repository.AccidentDatabaseRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Сервис для работы с трафиком и ДТП.
 */
public class AccidentService {

    /**
     * Репозиторий.
     */
    private final AccidentDatabaseRepository repository;

    public AccidentService(AccidentDatabaseRepository repository) {
        this.repository = repository;
    }

    /**
     * Заполнение базы данных из двух xml файлов.
     * Если не найдена временная точка в трафике для ДТП, загруженность автоматически заполняется значением 0.00.
     *
     * @param trafficsXml  файл с трафиками
     * @param accidentsXml файл с ДТП
     * @throws IOException если возникла ошибка с чтением xml файла
     */
    public void addAccidentsToDatabase(Path trafficsXml, Path accidentsXml) throws IOException {
        JaxbConverter jaxbConverter = new JaxbConverter();
        TrafficListIn trafficsXmlList = jaxbConverter.fromXml(Files.readString(trafficsXml), TrafficListIn.class);
        AccidentListIn accidentsXmlList = jaxbConverter.fromXml(Files.readString(accidentsXml), AccidentListIn.class);

        List<AccidentIn> accidents = accidentsXmlList.getAccidents();
        List<TrafficIn> traffics = trafficsXmlList.getTraffics();
        List<AccidentOut> accidentsOut = new ArrayList<>();
        for (AccidentIn accidentIn : accidents) {
            for (TrafficIn trafficIn : traffics) {
                if (accidentIn.getTimestamp().equals(trafficIn.getTimestamp())) {
                    AccidentOut accident = new AccidentOut();
                    accident.setTimestamp(accidentIn.getTimestamp());
                    accident.setCoefficientWorkload(trafficIn.getValue());
                    accident.setInfo(accidentIn.getEvent());
                    accidentsOut.add(accident);
                }
            }
        }
        repository.createMany(accidentsOut);
    }

    /**
     * Поиск всех ДТП из базы данных.
     */
    public List<AccidentOut> findAll() {
//        accidents.forEach(accidentOut -> System.out.println(accidentOut.toString()));
        return repository.findAll();
    }

    /**
     * Поиск ДТП по заданному времени.
     *
     * @param time время
     * @return {@code True}, если найдено, {@code False} иначе
     */
    public boolean findByTime(LocalDateTime time) {
        AccidentOut accident = repository.findByTime(time);
        if (Objects.isNull(accident)) {
            System.out.println("Происшествия в это время не случалось");
            return false;
        }
        System.out.println(accident);
        return true;
    }

    /**
     * Очищение БД.
     */
    public void deleteAll() {
        repository.deleteAll();
    }
}
