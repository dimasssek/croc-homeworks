package ru.croc.javaschool.autoparking.parkhub;

/**
 * Автомобиль.
 *
 * @author dimasssek
 */
public class Auto {
    /**
     * Номерной знак автомобиля.
     */
    private String numberOfAuto;

    /**
     * Создает {@link Auto}
     *
     * @param numberOfAuto номер автомобиля.
     */
    public Auto(String numberOfAuto) {
        this.numberOfAuto = numberOfAuto;
    }

    /**
     * Возвращает номер автомобиля.
     *
     * @return номер автомобиля.
     */
    public String getNumberOfAuto() {
        return numberOfAuto;
    }

    /**
     * Формирует строку с номером автомобиля.
     *
     * @return строка с номером автомобиля.
     */
    @Override
    public String toString() {
        return "Number: " + numberOfAuto;
    }
}
