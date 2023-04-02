package ru.croc.javaschool.software.vehicle.auto;


import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;

/**
 * Автомобиль.
 * <p>
 * Легковой автомобиль.
 */
public class Car extends Auto {
    /**
     * Количество сидений легкового автомобиля.
     */
    private final int numberOfSeats;
    /**
     * Количество лошадиных сил.
     */
    private int amountHorsepower;

    /**
     * Создаёт {@link Car}
     *
     * @param nameOfVehicle    название легкогого автомобиля.
     * @param vehicleCategory  категория легкогого автомобиля.
     * @param vehicleState     состояние легкогого автомобиля.
     * @param numberOfAuto     номер легкогого автомобиля.
     * @param brand            марка легкогого автомобиля.
     * @param numberOfSeats    количество сидений.
     * @param amountHorsepower количество лошадиных сил.
     */
    public Car(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState,
            String numberOfAuto,
            String brand,
            int numberOfSeats,
            int amountHorsepower
    ) {
        super(nameOfVehicle, vehicleCategory, vehicleState, numberOfAuto, brand);
        this.numberOfSeats = numberOfSeats;
        this.amountHorsepower = amountHorsepower;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getAmountHorsepower() {
        return amountHorsepower;
    }

    @Override
    public String toString() {
        return "Легковой автомобиль {" +
                "id = " + this.getIdVehicle() +
                "название='" + getNameOfVehicle() + '\'' +
                ", категория=" + getVehicleCategory() +
                ", состояние=" + getVehicleState() +
                ", номер='" + getNumberOfAuto() + '\'' +
                ", марка='" + getBrand() + '\'' +
                ", количество сидений=" + getNumberOfSeats() +
                ", количество лошадиных сил=" + getAmountHorsepower() +
                '}';
    }
}
