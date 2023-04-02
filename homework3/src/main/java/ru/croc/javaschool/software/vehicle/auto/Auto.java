package ru.croc.javaschool.software.vehicle.auto;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.vehicle.Vehicle;

/**
 * Автомобиль.
 */
class Auto extends Vehicle {
    /**
     * Номер автомобиля.
     */
    private String numberOfAuto;
    /**
     * Марка автомобиля.
     */
    private final String brand;

    /**
     * Создаёт {@link Auto}
     *
     * @param nameOfVehicle   название автомобиля.
     * @param vehicleCategory категория автомобиля.
     * @param vehicleState    состояние автомобиля.
     * @param numberOfAuto    номер автомобиля.
     * @param brand           марка автомобиля.
     */
    public Auto(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String numberOfAuto,
            String brand) {
        super(nameOfVehicle, vehicleCategory, vehicleState);
        this.numberOfAuto = numberOfAuto;
        this.brand = brand;
    }

    public String getNumberOfAuto() {
        return numberOfAuto;
    }

    public String getBrand() {
        return brand;
    }
}
