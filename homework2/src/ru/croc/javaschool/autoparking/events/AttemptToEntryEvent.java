package ru.croc.javaschool.autoparking.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Попытка въезда на парковку.
 *
 * @author dimasssek
 */
public class AttemptToEntryEvent {
    /**
     * Номер автомобиля, пытающегося въехать на парковку.
     */
    private String numberOfAuto;
    /**
     * Время, когда автомобиль пытался въехать на парковку.
     */
    private LocalDateTime time;
    /**
     * Успешность попытки въезда.
     */
    private boolean successOfAttempt;

    /**
     * Создаёт {@link AttemptToEntryEvent}
     *
     * @param numberOfAuto     номер автомобиля.
     * @param time             время въезда.
     * @param successOfAttempt успешность въезда.
     */
    public AttemptToEntryEvent(String numberOfAuto, LocalDateTime time, boolean successOfAttempt) {
        this.numberOfAuto = numberOfAuto;
        this.time = time;
        this.successOfAttempt = successOfAttempt;
    }

    /**
     * Возвращает успешность въезда.
     *
     * @return успешность въезда.
     */
    public boolean isSuccessOfAttempt() {
        return successOfAttempt;
    }

    /**
     * Формирует строку с информацией о безуспешном въезде автомобиля в конкретное время.
     *
     * @return строка с номером автомобиля и временем въезда.
     */
    @Override
    public String toString() {
        return "Unsuccessfully attempt to entry by car: " + numberOfAuto + " at " +
                time.format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss", Locale.US));
    }
}
