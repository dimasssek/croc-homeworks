package ru.croc.javaschool.software.vehicle.personaltransport;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.vehicle.Vehicle;


/**
 * Средство индивидуальной мобильности.
 * <p>
 * Электросамокат.
 */
public class ElectricScooter extends Vehicle {
    /**
     * Страна-производитель.
     */
    private final String country;
    /**
     * Максимальная скорость.
     */
    private int maxSpeed;

    /**
     * Создаёт {@link ElectricScooter}
     *
     * @param nameOfVehicle   название электросамоката.
     * @param vehicleCategory категория электросамоката.
     * @param vehicleState    состояние электросамоката.
     * @param country         страна-производитель электросамоката.
     * @param maxSpeed        максимальная скорость электросамоката.
     */
    public ElectricScooter(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String country,
            int maxSpeed
    ) {
        super(nameOfVehicle, vehicleCategory, vehicleState);
        this.country = country;
        this.maxSpeed = maxSpeed;
    }

    public String getCountry() {
        return country;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Электросамокат" + "{" +
                "id = " + this.getIdVehicle() +
                "название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", страна-производитель=" + getCountry() +
                ", максимальная скорость= " + getMaxSpeed() +
                '}';
    }
}
