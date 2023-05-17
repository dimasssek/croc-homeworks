package ru.croc.javaschool.finalhomework.expected;

import ru.croc.javaschool.finalhomework.model.AccidentOut;
import ru.croc.javaschool.finalhomework.repository.DerbyAccidentDatabaseRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Набор списков с ДТП для удобства тестирования БД.
 */
public class AccidentsLists {
    /**
     * Список для инициализации в сервисе.
     */
    private final List<AccidentOut> initialAccidentsService = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 7, 0, 0),
                    new BigDecimal("0.62"),
                    "Столкнулись 2 мусоровоза"),
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 8, 0, 0),
                    new BigDecimal("0.4"),
                    "Столкнулись 4 легковушки"),
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 11, 1, 0),
                    null,
                    "Камри сбила велосипедиста")
    ));

    /**
     * Список для инициализации в репозитории.
     */
    private final List<AccidentOut> initialAccidents = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    new BigDecimal("0.66"),
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    new BigDecimal("0.89"),
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    new BigDecimal("0.42"),
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    new BigDecimal("0.1"),
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    new BigDecimal("1.0"),
                    "Столкнулись 2 президентских кортежа")
    ));

    /**
     * Список для проверки {@link DerbyAccidentDatabaseRepository#findAll()}
     */
    private final List<AccidentOut> expectedFindAll = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    new BigDecimal("0.66"),
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    new BigDecimal("0.89"),
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    new BigDecimal("0.42"),
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    new BigDecimal("0.1"),
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    new BigDecimal("1.0"),
                    "Столкнулись 2 президентских кортежа")
    ));
    /**
     * Список для проверки {@link DerbyAccidentDatabaseRepository#create(AccidentOut)}.
     */
    private final List<AccidentOut> expectedCreate = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    new BigDecimal("0.66"),
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    new BigDecimal("0.89"),
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    new BigDecimal("0.42"),
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    new BigDecimal("0.1"),
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    new BigDecimal("1.0"),
                    "Столкнулись 2 президентских кортежа"),
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 23, 59, 0),
                    new BigDecimal("0.02"),
                    "Сбили 2 пешеходов")
    ));

    /**
     * Вспомогательный метод для инициализации ДТП без id.
     * @param timestamp временная точка
     * @param coefficientWorkload коэффициент загруженности
     * @param info информация о ДТП
     * @return ДТП
     */
    private AccidentOut createAccident(LocalDateTime timestamp, BigDecimal coefficientWorkload, String info) {
        AccidentOut accident = new AccidentOut();
        accident.setTimestamp(timestamp);
        accident.setCoefficientWorkload(coefficientWorkload);
        accident.setInfo(info);
        return accident;
    }

    private final List<AccidentOut> emptyList = new ArrayList<>();

    public List<AccidentOut> getEmptyList() {
        return emptyList;
    }

    public List<AccidentOut> getInitialAccidents() {
        return initialAccidents;
    }

    public List<AccidentOut> getInitialAccidentsService() {
        return initialAccidentsService;
    }

    public List<AccidentOut> getExpectedFindAll() {
        return expectedFindAll;
    }

    public List<AccidentOut> getExpectedCreate() {
        return expectedCreate;
    }
}
