package ru.croc.javaschool.autoparking.parkhub.points;

import ru.croc.javaschool.autoparking.parkhub.Auto;

/**
 * Пункт въезда.
 *
 * @author dimasssek
 */
public class EntryPoint {
    /**
     * Номер въезда.
     */
    private String codeOfEntry;
    /**
     * Информация о въезде.
     */
    private String info;
    /**
     * Автомобили, проехавшие через указанный въезд.
     */
    private Auto[] autos;

    /**
     * Создаёт {@link EntryPoint}
     *
     * @param codeOfEntry номер въезда.
     * @param info        информация о въезде.
     * @param autos       автомобили, проехавшие через въезд.
     */
    public EntryPoint(String codeOfEntry, String info, Auto[] autos) {
        this.codeOfEntry = codeOfEntry;
        this.info = info;
        this.autos = autos;
    }

    /**
     * Номера машин, которые проехали через указанный въезд.
     */
    public void numbersOfCarsPassing() {
        for (Auto auto : autos) {
            System.out.println("From entry " + codeOfEntry + " come in auto. " + auto.toString());
        }
    }

    /**
     * Добавление нового автомобиля в массив автомобилей, проехавших через данный въезд.
     * В случае переполнения массива используется расширение массива на 1 объект.
     *
     * @param auto автомобиль.
     */
    public void addToEntryAutosArray(Auto auto) {
        boolean added = false;

        for (int i = 0; i < autos.length; i++) {
            if (autos[i] == null) {
                autos[i] = auto;
                added = true;
                break;
            }
        }

        if (!added) {
            Auto[] newArrayOfAutos = new Auto[autos.length + 1];
            System.arraycopy(autos, 0, newArrayOfAutos, 0, autos.length);
            this.autos = newArrayOfAutos;
            autos[autos.length - 1] = auto;
        }
    }
}
