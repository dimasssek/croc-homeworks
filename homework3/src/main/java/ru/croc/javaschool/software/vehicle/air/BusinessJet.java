package ru.croc.javaschool.software.vehicle.air;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.vehicle.Vehicle;

/**
 * Летательное транспортное средство.
 * <p>
 * Бизнес-джет.
 */
public class BusinessJet extends Vehicle {
    /**
     * Цвет бизнес-джета.
     */
    private String color;
    /**
     * Максимальная высота подъема бизнес-джета.
     */
    private final int maxHeight;

    /**
     * Создаёт {@link BusinessJet}
     *
     * @param nameOfVehicle   название бизнес-джета.
     * @param vehicleCategory категория бизнес-джета.
     * @param vehicleState    состояние бизнес-джета.
     * @param color           цвет бизнес-джета.
     * @param maxHeight       максимальная высота подъема бизнес-джета.
     */
    public BusinessJet(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String color,
            int maxHeight) {
        super(nameOfVehicle, vehicleCategory, vehicleState);
        this.color = color;
        this.maxHeight = maxHeight;
    }

    public String getColor() {
        return color;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public String toString() {
        return "Вертолет" + "{" +
                "id = " + this.getIdVehicle() +
                "название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", цвет бизнес-джета=" + getColor() +
                ", максимальная высота подъема = " + getMaxHeight() +
                '}';
    }
}
