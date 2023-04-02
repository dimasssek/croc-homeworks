package ru.croc.javaschool.autoparking;

import ru.croc.javaschool.autoparking.parkhub.Auto;
import ru.croc.javaschool.autoparking.parkhub.points.EntryPoint;
import ru.croc.javaschool.autoparking.parkhub.points.ExitPoint;
import ru.croc.javaschool.autoparking.parkhub.Parking;
import ru.croc.javaschool.autoparking.events.AttemptToEntryEvent;

/**
 * Класс для запуска демо-сценария.
 *
 * @author dimasssek
 */
public class DemoScene {
    public static void main(String[] args) {
        /*
          Выделение памяти и инициализация.
         */
        Auto[] autos = {
                new Auto("ABC123"),
                new Auto("XYZ789"),
                new Auto("GHI456"),
                new Auto("JKL012"),
                new Auto("MNO345"),
                new Auto("PQR678"),
                new Auto("STU901"),
                new Auto("VWX234"),
                new Auto("YZA567"),
                new Auto("BCD890"),
                new Auto("AZA667"),
                new Auto("ITE001")
        };

        EntryPoint[] entryPoints = {
                new EntryPoint("A1", "South", new Auto[0]),
                new EntryPoint("A2", "West", new Auto[0]),
                new EntryPoint("A3", "East", new Auto[0]),
                new EntryPoint("A4", "North", new Auto[0])
        };

        ExitPoint[] exitPoints = {
                new ExitPoint("B1", "South", new Auto[0]),
                new ExitPoint("B2", "West", new Auto[0]),
                new ExitPoint("B3", "East", new Auto[0]),
                new ExitPoint("B4", "North", new Auto[0])
        };


        AttemptToEntryEvent[] attemptToEntryEvents = new AttemptToEntryEvent[1];
        int numberOfPlaces = 10;
        Parking parking = new Parking(
                autos,
                entryPoints,
                exitPoints,
                numberOfPlaces,
                attemptToEntryEvents);

        /*
          Демонстрируется:
          Возможность въезда на парковку(по наличию свободных мест);
          Количество свободных мест на парковке.
         */
        parking.printMessageAboutFreePlaces();

        /*
          Демонстрируется:
          Въезд автомобиля с номером XXX через определенный въезд
          Возможность узнать список машин проехавших через указанный въезд
         */
        for (int index = 0; index < 9; index++) {
            parking.autoEntry(autos[index], entryPoints[index % 4]);
        }

        for (EntryPoint entryPoint : entryPoints) {
            entryPoint.numbersOfCarsPassing();
            System.out.println("That`s all.");
        }

        /*
          Демонстрируется:
          Выезд автомобиля с номером XXX через определенный выезд
          Возможность узнать список машин проехавших через указанный выезд
         */
        for (int index = 0; index < 9; index++) {
            parking.autoExit(autos[index], exitPoints[index % 4]);
        }

        for (ExitPoint exitPoint : exitPoints) {
            exitPoint.numbersOfCarsPassing();
            System.out.println("That`s all.");
        }

        /*
          Демонстрируется:
          Возможность узнать список попыток, с указанием номера машины и времени,
          когда машина не смогла попасть на парковку.
         */
        for (int index = 0; index < autos.length; index++) {
            parking.autoEntry(autos[autos.length - 1 - index], entryPoints[index % 4]);
        }

        parking.printMessageAboutFreePlaces();
        parking.listOfUnsuccessfullyAttempts();
    }
}
