package ru.croc.javaschool.finalhomework.expected;

import ru.croc.javaschool.finalhomework.model.entity.AccidentOut;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Набор списков с ДТП для удобства тестирования БД.
 */
public class AccidentsLists {

    private final ArrayList<AccidentOut> initialAccidentsService = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 7, 0, 0),
                    0.62,
                    "Столкнулись 2 мусоровоза"),
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 8, 0, 0),
                    0.40,
                    "Столкнулись 2 мусоровоза"),
            createAccident(
                    LocalDateTime.of(2023, 5, 13, 11, 1, 0),
                    0.00,
                    "Столкнулись 2 мусоровоза")
    ));

    private final ArrayList<AccidentOut> initialAccidents = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    0.66,
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    0.89,
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    0.42,
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    0.10,
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    1.00,
                    "Столкнулись 2 президентских кортежа")
    ));

    private final ArrayList<AccidentOut> expectedFindAll = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    0.66,
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    0.89,
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    0.42,
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    0.10,
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    1.00,
                    "Столкнулись 2 президентских кортежа")
    ));

    private final ArrayList<AccidentOut> expectedCreate = new ArrayList<>(Arrays.asList(
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 7, 0, 0),
                    0.66,
                    "Столкнулись 2 мусоровоза"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 8, 0, 0),
                    0.89,
                    "Столкнулись 4 легковушки"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 11, 15, 0),
                    0.42,
                    "Камри сбила велосипедиста"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 16, 23, 17),
                    0.10,
                    "Ламборгини протаранила Феррари"),

            createAccident(
                    LocalDateTime.of(2023, 5, 15, 22, 22, 22),
                    1.00,
                    "Столкнулись 2 президентских кортежа"),
            createAccident(
                    LocalDateTime.of(2023, 5, 15, 23, 59, 0),
                    0.02,
                    "Сбили 2 пешеходов")
    ));

    private final AccidentOut expectedByTime = createAccident(
            LocalDateTime.of(2023, 5, 15, 7, 0, 0),
            0.66,
            "Столкнулись 2 мусоровоза");

    private AccidentOut createAccident(LocalDateTime timestamp, Double coefficientWorkload, String info) {
        AccidentOut accident = new AccidentOut();
        accident.setTimestamp(timestamp);
        accident.setCoefficientWorkload(coefficientWorkload);
        accident.setInfo(info);
        return accident;
    }

    public ArrayList<AccidentOut> getInitialAccidents() {
        return initialAccidents;
    }

    public ArrayList<AccidentOut> getExpectedFindAll() {
        return expectedFindAll;
    }

    public ArrayList<AccidentOut> getExpectedCreate() {
        return expectedCreate;
    }

    public AccidentOut getExpectedByTime() {
        return expectedByTime;
    }

    public ArrayList<AccidentOut> getInitialAccidentsService() {
        return initialAccidentsService;
    }
}
