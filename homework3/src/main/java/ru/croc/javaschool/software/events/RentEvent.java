package ru.croc.javaschool.software.events;

import java.time.LocalDate;

import ru.croc.javaschool.software.vehicle.Vehicle;

/**
 * Аренда транспортного средства.
 */
public class RentEvent {
    /**
     * Дата начала аренды.
     */
    private LocalDate startOfRent;
    /**
     * Дата окончания аренды.
     */
    private LocalDate dayOfEndRent;
    /**
     * Транспортное средство {@link Vehicle}
     */
    private Vehicle vehicle;

    /**
     * Создает {@link RentEvent}
     *
     * @param startOfRent  дата начала аренды.
     * @param dayOfEndRent дата окончания аренды.
     * @param vehicle      транспортное средства.
     */
    public RentEvent(LocalDate startOfRent, LocalDate dayOfEndRent, Vehicle vehicle) {
        this.startOfRent = startOfRent;
        this.dayOfEndRent = dayOfEndRent;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Транспортное средство " +
                vehicle.getNameOfVehicle() + "(" + "id = " + vehicle.getIdVehicle() + ")"
                + " забронировано с " + startOfRent + " по " + dayOfEndRent;
    }

    public LocalDate getStartOfRent() {
        return startOfRent;
    }

    public LocalDate getDayOfEndRent() {
        return dayOfEndRent;
    }
}
