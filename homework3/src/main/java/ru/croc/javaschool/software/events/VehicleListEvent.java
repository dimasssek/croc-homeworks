package ru.croc.javaschool.software.events;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Список всех транспортных средств.
 */
public class VehicleListEvent implements AccountingOperations, Reportable {
    private List<Vehicle> vehicles;

    /**
     * Инициализирует {@link VehicleListEvent}
     */
    public VehicleListEvent() {
        vehicles = new ArrayList<>();
    }

    /**
     * Вспомогательная функция для подсчёта транспротных единиц по категориям.
     *
     * @param vehicleCategory категория транспорта
     * @param date дата
     * @return количество транспорта указанной категории
     */
    private int counterOfFreeVehicle(VehicleCategory vehicleCategory, LocalDate date) {
        int counter = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleCategory() == vehicleCategory)
                if (vehicle.isFree(date))
                    counter++;
        }
        return counter;
    }

    /**
     * Вспомогательная функция для вывода на экран по категориям
     *
     * @param startDate       дата начала.
     * @param endDate         дата конца.
     * @param vehicles        список по категории.
     * @param vehicleCategory категория.
     */
    private void printByCategory(LocalDate startDate, LocalDate endDate,
                                 List<Vehicle> vehicles, VehicleCategory vehicleCategory) {
        if (vehicles.isEmpty())
            System.out.println("Свободных единиц транспорта среди категории " + vehicleCategory + " нет" + "\n");
        else {
            System.out.println("Среди катгории " + vehicleCategory + " доступны:" + "\n");
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isFree(startDate, endDate))
                    System.out.println(vehicle);
            }
        }
    }

    /**
     * Вспомогательная функция для деления по категориям
     *
     * @return список списков по категориям.
     */
    private List<List<Vehicle>> createCategories() {
        List<Vehicle> autos = new ArrayList<>();
        List<Vehicle> personalTransports = new ArrayList<>();
        List<Vehicle> airs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleCategory() == VehicleCategory.AUTO) {
                autos.add(vehicle);
            } else if (vehicle.getVehicleCategory() == VehicleCategory.AIRCRAFT) {
                airs.add(vehicle);
            } else {
                personalTransports.add(vehicle);
            }
        }
        List<List<Vehicle>> categories = new ArrayList<>();
        categories.add(autos);
        categories.add(personalTransports);
        categories.add(airs);
        return categories;
    }

    @Override
    public void registration(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void remove(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void createReport(LocalDate date) {
        List<List<Vehicle>> categories = createCategories();
        System.out.println("Для категории " + VehicleCategory.AUTO + " доступно всего " + categories.get(0).size());
        System.out.println("Из них них " +
                (categories.get(0).size() - counterOfFreeVehicle(VehicleCategory.AUTO, date))
                + " забронированы на " + date);
        System.out.println("Для категории " + VehicleCategory.SIMPLE + " доступно всего " + categories.get(1).size());
        System.out.println("Из них них " +
                (categories.get(1).size() - counterOfFreeVehicle(VehicleCategory.SIMPLE, date))
                + " забронированы на " + date);
        System.out.println("Для категории " + VehicleCategory.AIRCRAFT + " доступно всего " + categories.get(2).size());
        System.out.println("Из них них " +
                (categories.get(2).size() - counterOfFreeVehicle(VehicleCategory.AIRCRAFT, date))
                + " забронированы на " + date);
    }

    @Override
    public void toCategories(LocalDate dateOfStartRent, LocalDate dateOfEndRent) {
        List<List<Vehicle>> categories = createCategories();
        printByCategory(dateOfStartRent, dateOfEndRent, categories.get(0), VehicleCategory.AUTO);
        System.out.println("-----------------------------------------");
        printByCategory(dateOfStartRent, dateOfEndRent, categories.get(1), VehicleCategory.SIMPLE);
        System.out.println("-----------------------------------------");
        printByCategory(dateOfStartRent, dateOfEndRent, categories.get(2), VehicleCategory.AIRCRAFT);
    }

    @Override
    public void trackRentedVehicles(LocalDate dateOfStartRent, LocalDate dateOfEndRent) {
        for (Vehicle vehicle : vehicles) {
            for (RentEvent rent : vehicle.getRents())
                if (!vehicle.isFree(dateOfStartRent, dateOfEndRent)) {
                    System.out.println(rent.toString());
                    break;
                }
        }
    }

    /**
     * Выводит на экран список транспортных средств.
     */
    public void printListOfVehicles() {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString() + "\n");
        }
    }

    /**
     * Возвращает транспортное средство из списка по индексу.
     *
     * @param index индекс.
     * @return транспортное средство.
     */
    public Vehicle getByIndex(int index) {
        return vehicles.get(index);
    }
}
