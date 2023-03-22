package ru.croc.javaschool.autoparking.parkhub;

import ru.croc.javaschool.autoparking.events.AttemptToEntryEvent;
import ru.croc.javaschool.autoparking.parkhub.points.EntryPoint;
import ru.croc.javaschool.autoparking.parkhub.points.ExitPoint;

import java.time.LocalDateTime;

/**
 * Автомобильная парковка.
 *
 * @author dimasssek
 */
public class Parking {
    /**
     * Автомобили, находящиеся на парковке.
     */
    private Auto[] autos;
    /**
     * Пункты въезда на парковку.
     */
    private EntryPoint[] entryPoints;
    /**
     * Пункты выезда с парковки.
     */
    private ExitPoint[] exitPoints;
    /**
     * Количество свободных мест на парковке.
     */
    private int numberOfPlaces;
    /**
     * Список всех попыток автомобилей попасть через въезд на парковку
     */
    private AttemptToEntryEvent[] attemptsToEntry;

    /**
     * Создает {@link Parking}
     *
     * @param autos           автомобили.
     * @param entryPoints     точки въезда.
     * @param exitPoints      точки выезда.
     * @param numberOfPlaces  количество свободных мест.
     * @param attemptsToEntry попытки въезда автомобилей.
     */
    public Parking(Auto[] autos, EntryPoint[] entryPoints, ExitPoint[] exitPoints,
                   int numberOfPlaces, AttemptToEntryEvent[] attemptsToEntry) {
        this.autos = autos;
        this.entryPoints = entryPoints;
        this.exitPoints = exitPoints;
        this.numberOfPlaces = numberOfPlaces;
        this.attemptsToEntry = attemptsToEntry;
    }

    /**
     * Изменяет количество свободных мест на парковке.
     *
     * @param numberOfPlaces количество свободных мест.
     */
    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    /**
     * Возвращает количество свободных мест.
     * Пояснение: геттер для того, чтобы
     *
     * @return количество свободных мест.
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Проверка наличия свободного места на парковке.
     *
     * @return возможность въезда на парковку.
     */
    public boolean isEmptyParking() {
        return getNumberOfPlaces() != 0;
    }

    /**
     * Въезд автомобиля через определенный въезд.
     * <p>
     * Замечание: Т.к. занятия про потоки еще не было, метод sleep() для указания разного временного промежутка
     * не использовался. Поэтому используется переменная randomValue для дифференцирования временных точек.
     *
     * @param auto       автомобиль.
     * @param entryPoint въезд.
     */
    public void autoEntry(Auto auto, EntryPoint entryPoint) {
        if (isEmptyParking()) {
            addAttemptToEntry(new AttemptToEntryEvent(auto.getNumberOfAuto(),
                    LocalDateTime.now(), true));
            setNumberOfPlaces(getNumberOfPlaces() - 1);
            entryPoint.addToEntryAutosArray(auto);
        } else {
            addAttemptToEntry(new AttemptToEntryEvent(auto.getNumberOfAuto(),
                    LocalDateTime.now(), false));
        }
    }

    /**
     * Выезд автомобиля через определенный выезд.
     *
     * @param auto      автомобиль.
     * @param exitPoint выезд.
     */
    public void autoExit(Auto auto, ExitPoint exitPoint) {
        exitPoint.addToExitAutosArray(auto);
        setNumberOfPlaces(getNumberOfPlaces() + 1);
    }

    /**
     * Добавление новой попытки в массив.
     * В случае переполнения массива используется расширение массива на 1 объект.
     *
     * @param attempt попытка въезда.
     */
    private void addAttemptToEntry(AttemptToEntryEvent attempt) {
        boolean added = false;

        for (int i = 0; i < attemptsToEntry.length; i++) {
            if (attemptsToEntry[i] == null) {
                attemptsToEntry[i] = attempt;
                added = true;
                break;
            }
        }

        if (!added) {
            AttemptToEntryEvent[] newAttemptsToEntry = new AttemptToEntryEvent[attemptsToEntry.length + 1];
            System.arraycopy(attemptsToEntry, 0, newAttemptsToEntry, 0, attemptsToEntry.length);
            this.attemptsToEntry = newAttemptsToEntry;
            attemptsToEntry[attemptsToEntry.length - 1] = attempt;
        }
    }

    /**
     * Вывод списка попыток въезда автомобиля в определенное время.
     */
    public void listOfUnsuccessfullyAttempts() {
        for (AttemptToEntryEvent attempt : attemptsToEntry) {
            if (!attempt.isSuccessOfAttempt())
                System.out.println(attempt.toString());
        }
    }

    /**
     * Сообщение о возможности въезда на парковку с указанием количества свободных мест.
     */
    public void printMessageAboutFreePlaces() {
        if (isEmptyParking()) {
            System.out.println("Welcome! We have " + getNumberOfPlaces() + " places!");
        } else {
            System.out.println("Sorry! There is no free places for your auto :(");
        }
    }
}
