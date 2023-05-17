package ru.croc.javaschool.finalhomework.service;

import ru.croc.javaschool.finalhomework.data.xml.JaxbConverter;
import ru.croc.javaschool.finalhomework.dto.AccidentIn;
import ru.croc.javaschool.finalhomework.dto.AccidentListIn;
import ru.croc.javaschool.finalhomework.dto.TrafficIn;
import ru.croc.javaschool.finalhomework.dto.TrafficListIn;
import ru.croc.javaschool.finalhomework.model.AccidentOut;
import ru.croc.javaschool.finalhomework.repository.AccidentDatabaseRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
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
    /**
     * Конвертер для xml-файлов.
     */
    private final JaxbConverter jaxbConverter;

    /**
     * Создаёт {@link AccidentService}.
     *
     * @param repository репозиторий
     */
    public AccidentService(AccidentDatabaseRepository repository) {
        this.repository = repository;
        this.jaxbConverter = new JaxbConverter();
    }

    /**
     * Заполнение базы данных из двух xml файлов.
     * Если не найдена временная точка в трафике для ДТП, загруженность автоматически заполняется значением 0.00.
     *
     * @param trafficsXml  файл с трафиками
     * @param accidentsXml файл с ДТП
     * @throws IOException если возникла ошибка с чтением xml файла
     */
    public void addAccidentsToDatabase(Path trafficsXml, Path accidentsXml) throws IOException, SQLException {
        var trafficsXmlList = jaxbConverter.fromXml(Files.readString(trafficsXml), TrafficListIn.class);
        var accidentsXmlList = jaxbConverter.fromXml(Files.readString(accidentsXml), AccidentListIn.class);
        var accidentsOut = new ArrayList<AccidentOut>();

        var accidents = new HashMap<LocalDateTime, AccidentIn>();
        var traffics = new HashMap<LocalDateTime, TrafficIn>();

        for (AccidentIn accident : accidentsXmlList.getAccidents()) {
            accidents.put(accident.getTimestamp(), accident);
        }

        for (TrafficIn traffic : trafficsXmlList.getTraffics()) {
            traffics.put(traffic.getTimestamp(), traffic);
        }

        for (Map.Entry<LocalDateTime, AccidentIn> entry: accidents.entrySet()) {
            AccidentOut accident = new AccidentOut();
            accident.setTimestamp(entry.getKey());
            accident.setInfo(entry.getValue().getEvent());
            if (traffics.containsKey(entry.getKey())) {
                accident.setCoefficientWorkload(new BigDecimal(traffics.get(entry.getKey()).getValue()));
            } else {
                accident.setCoefficientWorkload(null);
            }
            accidentsOut.add(accident);
        }
        repository.createMany(accidentsOut);
    }

    /**
     * Поиск всех ДТП из базы данных.
     */
    public List<AccidentOut> findAll() throws SQLException {
//        accidents.forEach(accidentOut -> System.out.println(accidentOut.toString()));
        return repository.findAll();
    }

    /**
     * Поиск ДТП по заданному времени.
     *
     * @param time время
     * @return {@code True}, если найдено, {@code False} иначе
     */
    public boolean findByTime(LocalDateTime time) throws SQLException {
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
    public void deleteAll() throws SQLException {
        repository.deleteAll();
    }
}
