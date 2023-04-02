package ru.croc.javaschool.software.vehicle.auto;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;

/**
 * Автомобиль.
 * <p>
 * Грузовой автомобиль.
 */
public class Truck extends Auto {
    /**
     * Максимальная грузоподъемность.
     */
    private int maxCapacity;
    /**
     * Высота кузова.
     */
    private int height;

    /**
     * Создаёт {@link Truck}
     *
     * @param nameOfVehicle   название грузового автомобиля.
     * @param vehicleCategory категория грузового автомобиля.
     * @param vehicleState    состояние грузового автомобиля.
     * @param numberOfAuto    номер грузового автомобиля.
     * @param brand           марка грузового автомобиля.
     * @param maxCapacity     максимальная грузоподъемность.
     * @param height          высота кузова грузового автомобиля.
     */
    public Truck(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String numberOfAuto,
            String brand,
            int maxCapacity,
            int height
    ) {
        super(nameOfVehicle, vehicleCategory, vehicleState, numberOfAuto, brand);
        this.maxCapacity = maxCapacity;
        this.height = height;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Грузовой автомобиль {" +
                "id = " + this.getIdVehicle() +
                "название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", номер автомобиля='" + getNumberOfAuto() + '\'' +
                ", марка='" + getBrand() + '\'' +
                ", максимальная грузоподъемность=" + getMaxCapacity() +
                ", высота кузова=" + getHeight() +
                '}';
    }
}
