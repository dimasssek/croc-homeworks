package ru.croc.javaschool.software.vehicle.air;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.vehicle.Vehicle;

/**
 * Летательное транспортное средство.
 * <p>
 * Вертолёт.
 */
public class Helicopter extends Vehicle {
    /**
     * Марка вертолёта.
     */
    private final String brand;
    /**
     * Максимальная скорость полёта.
     */
    private int maxSpeed;

    /**
     * Создаёт {@link Helicopter}
     *
     * @param nameOfVehicle   название вертолёта.
     * @param vehicleCategory категория вертолёта.
     * @param vehicleState    состояние вертолёта.
     * @param brand           марка вертолёта.
     * @param maxSpeed        максимальная скорость полёта.
     */
    public Helicopter(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String brand,
            int maxSpeed
    ) {
        super(nameOfVehicle, vehicleCategory, vehicleState);
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Вертолет" + "{" +
                "id = " + this.getIdVehicle() +
                "название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", марка вертолёта=" + getBrand() +
                ", максимальная скорость полёта = " + getMaxSpeed() +
                '}';
    }
}
