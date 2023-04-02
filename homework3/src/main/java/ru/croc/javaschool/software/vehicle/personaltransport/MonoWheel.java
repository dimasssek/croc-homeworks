package ru.croc.javaschool.software.vehicle.personaltransport;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.vehicle.Vehicle;


/**
 * Средство индивидуальной мобильности.
 * <p>
 * Моноколесо.
 */
public class MonoWheel extends Vehicle {
    /**
     * Год выпуска.
     */
    private final int year;
    /**
     * Номер модели.
     */
    private final String numberOfModel;

    /**
     * Создаёт {@link MonoWheel}
     *
     * @param nameOfVehicle   название моноколеса.
     * @param vehicleCategory категория моноколеса.
     * @param vehicleState    состояние моноколеса.
     * @param year            год выпуска моноколеса.
     * @param numberOfModel   номер модели моноколеса.
     */
    public MonoWheel(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            int year,
            String numberOfModel
    ) {
        super(nameOfVehicle, vehicleCategory, vehicleState);
        this.year = year;
        this.numberOfModel = numberOfModel;
    }

    public int getYear() {
        return year;
    }

    public String getNumberOfModel() {
        return numberOfModel;
    }

    @Override
    public String toString() {
        return "Моноколесо {" + "id = " + this.getIdVehicle() +
                " название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", год выпуска=" + getYear() +
                ", номер модели='" + getNumberOfModel() + '\'' +
                '}';
    }
}
