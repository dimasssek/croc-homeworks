package ru.croc.javaschool.software;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.events.RentEvent;
import ru.croc.javaschool.software.events.VehicleListEvent;
import ru.croc.javaschool.software.vehicle.air.BusinessJet;
import ru.croc.javaschool.software.vehicle.air.Helicopter;
import ru.croc.javaschool.software.vehicle.auto.Car;
import ru.croc.javaschool.software.vehicle.auto.Truck;
import ru.croc.javaschool.software.vehicle.personaltransport.ElectricScooter;
import ru.croc.javaschool.software.vehicle.personaltransport.MonoWheel;

import java.time.LocalDate;

/**
 * Демонстрационный класс.
 */
public class DemoScene {
    public static void main(String[] args) {
        VehicleListEvent vehicleList = new VehicleListEvent();

        /* Легковые автомобили*/
        vehicleList.registration(
                new Car("Vesta", VehicleCategory.AUTO, VehicleState.SERVICEABLE, "ABC123",
                        "Lada", 5, 200)
        );
        vehicleList.registration(
                new Car("Logan", VehicleCategory.AUTO, VehicleState.SERVICEABLE, "DEF123",
                        "Renault", 4, 150)
        );
        vehicleList.registration(
                new Car("Granta", VehicleCategory.AUTO, VehicleState.SERVICEABLE, "ZXC123",
                        "Lada", 5, 100)
        );

        /* Грузовые автомобили*/
        vehicleList.registration(
                new Truck("Gazel", VehicleCategory.AUTO, VehicleState.SERVICEABLE, "A1234B",
                        "Lada", 5000, 5)
        );
        vehicleList.registration(
                new Truck("Gazel", VehicleCategory.AUTO, VehicleState.SERVICEABLE, "C5678D",
                        "Lada", 5000, 5)
        );

        /* Моноколеса */
        vehicleList.registration(
                new MonoWheel("GigaCad", VehicleCategory.SIMPLE, VehicleState.SERVICEABLE,
                        2017, "MW1")
        );
        vehicleList.registration(
                new MonoWheel("CicaGad", VehicleCategory.SIMPLE, VehicleState.SERVICEABLE,
                        2021, "MW2")
        );

        /* Электросамокаты */
        vehicleList.registration(
                new ElectricScooter("El Pedro", VehicleCategory.SIMPLE, VehicleState.SERVICEABLE,
                        "China", 40)
        );
        vehicleList.registration(
                new ElectricScooter("El Chacha", VehicleCategory.SIMPLE, VehicleState.SERVICEABLE,
                        "UAE", 400)
        );

        /* Бизнес-джет, вертолёт. */
        vehicleList.registration(
                new BusinessJet("BJ", VehicleCategory.AIRCRAFT, VehicleState.SERVICEABLE,
                        "White", 4000)
        );
        vehicleList.registration(
                new Helicopter("HC", VehicleCategory.AIRCRAFT, VehicleState.SERVICEABLE,
                        "Boeing", 500)
        );


        /* Ломаем машины и удаляем их из-за неисправности. */
        vehicleList.getByIndex(2).setFaultyState();
        vehicleList.getByIndex(4).setFaultyState();
        vehicleList.remove(vehicleList.getByIndex(2));
        vehicleList.remove(vehicleList.getByIndex(4));
        // vehicleList.printListOfVehicles();

        /* Бронируем авто(успешно и неуспешно) */
        LocalDate dateFirst = LocalDate.of(2023, 4, 2);
        LocalDate dateSecond = dateFirst.plusDays(10);
        LocalDate dateThird = dateSecond.plusDays(2);
        LocalDate dateForth = dateThird.plusDays(3);
        vehicleList.getByIndex(0).rentVehicle(
                new RentEvent(dateFirst, dateSecond, vehicleList.getByIndex(0))
        );
        vehicleList.getByIndex(0).rentVehicle(
                new RentEvent(dateThird, dateForth, vehicleList.getByIndex(0))
        );
        vehicleList.getByIndex(0).rentVehicle(
                new RentEvent(dateFirst, dateSecond, vehicleList.getByIndex(0))
        );

        /*Выполняем пункты 4,5,6*/
        LocalDate checkDateFirst = LocalDate.of(2023, 4, 2);
        LocalDate checkDateSecond = LocalDate.of(2023, 4, 9);
        vehicleList.toCategories(checkDateFirst, checkDateSecond);
        System.out.println("-------------------------------------------");
        vehicleList.trackRentedVehicles(checkDateFirst, checkDateSecond);
        System.out.println("-------------------------------------------");
        vehicleList.createReport(checkDateSecond);
    }
}
