package ru.croc.javaschool.autoparking.parkhub.points;

import ru.croc.javaschool.autoparking.parkhub.Auto;

/**
 * Пункт выезда.
 *
 * @author dimasssek
 */
public class ExitPoint {
    /**
     * Номер выезда.
     */
    private String codeOfExit;
    /**
     * Информация о выезде.
     */
    private String info;
    /**
     * Автомобили, проехавшие через указанный выезд.
     */
    private Auto[] autos;

    /**
     * Создаёт {@link ExitPoint}
     *
     * @param codeOfExit номер выезда.
     * @param info       информация о выезде.
     * @param autos      автомобили, проехавшие через выезд.
     */
    public ExitPoint(String codeOfExit, String info, Auto[] autos) {
        this.codeOfExit = codeOfExit;
        this.info = info;
        this.autos = autos;
    }

    /**
     * Номера машин, которые выехали через указанный выезд.
     */
    public void numbersOfCarsPassing() {
        for (Auto auto : autos) {
            System.out.println("From exit " + codeOfExit + " left out auto. " + auto.toString());
        }
    }

    /**
     * Добавление нового автомобиля в массив автомобилей, проехавших через данный выезд.
     * В случае переполнения массива используется расширение массива на 1 объект.
     *
     * @param auto автомобиль.
     */
    public void addToExitAutosArray(Auto auto) {
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
