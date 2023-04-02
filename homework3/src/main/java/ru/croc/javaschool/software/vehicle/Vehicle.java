package ru.croc.javaschool.software.vehicle;

import ru.croc.javaschool.software.enums.VehicleCategory;
import ru.croc.javaschool.software.enums.VehicleState;
import ru.croc.javaschool.software.events.RentEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Транспортное средство.
 */
public abstract class Vehicle {
    /**
     * Уникальный идентификатор транспортного средства.
     */
    private final UUID idVehicle;
    /**
     * Название транспортного средства.
     */
    private String nameOfVehicle;
    /**
     * Категория транспортного средства.
     */
    private final VehicleCategory vehicleCategory;
    /**
     * Состояние транпортного средства.
     */
    private VehicleState vehicleState;
    /**
     * Список {@link RentEvent} на заданные даты для транспортного средства.
     */
    private List<RentEvent> rents;

    /**
     * Создаёт {@link Vehicle}.
     *
     * @param nameOfVehicle   название транспортного средства.
     * @param vehicleCategory категория транспортного средства.
     * @param vehicleState    состояние транпортного средства.
     */
    public Vehicle(
            String nameOfVehicle,
            VehicleCategory vehicleCategory,
            VehicleState vehicleState) {
        this.idVehicle = UUID.randomUUID();
        this.nameOfVehicle = nameOfVehicle;
        this.vehicleCategory = vehicleCategory;
        this.vehicleState = vehicleState;
        this.rents = new ArrayList<>();
    }

    /**
     * Возвращает уникальный идентификатор транспортного средства.
     *
     * @return уникальный идентификатор.
     */
    public UUID getIdVehicle() {
        return idVehicle;
    }

    /**
     * Возвращает категорию транспортного средства.
     *
     * @return категория транспортного средства.
     */
    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    /**
     * Возвращает состояние транспортного средства.
     *
     * @return состояние транспортного средства.
     */

    /**
     * Изменяет состояние на "Неисправное"
     */
    public void setFaultyState() {
        this.vehicleState = VehicleState.FAULTY;
    }

    /**
     * Выполняет аренду транспортного средства.
     *
     * @param rent аренда транспортного средства.
     */
    public void rentVehicle(RentEvent rent) {
        if (this.rents.isEmpty()) {
            rents.add(rent);
            System.out.println("Транспорт успешно забронирован.");
        }
        else if (this.isFree(rent.getStartOfRent(), rent.getDayOfEndRent())) {
            rents.add(rent);
            System.out.println("Транспорт успешно забронирован.");
        } else {
            System.out.println("Бронирование данного транспорта не может быть выполнено");
        }
    }

    /**
     * Проверка является ли транспорт свободным на указанную дату.
     *
     * @return true если свободно.
     */
    public boolean isFree(LocalDate date) {
        boolean isFree = true;
        for (RentEvent rent : rents) {
            if (date.isAfter(rent.getStartOfRent())|| date.isEqual(rent.getStartOfRent()) ||
            date.isBefore(rent.getDayOfEndRent())|| date.isEqual(rent.getDayOfEndRent())) {
                isFree = false;
                break;
            }
        }
        return isFree;
    }

    /**
     * Проверка является ли транспорт свободным на указанные даты.
     *
     * @return true, если свободно.
     */
    public boolean isFree(LocalDate startDate, LocalDate endDate) {
        boolean isFree = true;
        for (RentEvent rent : rents) {
            if (rent.getStartOfRent().isAfter(endDate) || rent.getDayOfEndRent().isAfter(startDate) ||
                    (rent.getStartOfRent().isEqual(startDate) || rent.getStartOfRent().isEqual(endDate)) ||
                    (rent.getDayOfEndRent().isEqual(startDate) || rent.getDayOfEndRent().isEqual(endDate))) {
                isFree = false;
                break;
            }
        }
        return isFree;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public String getNameOfVehicle() {
        return nameOfVehicle;
    }

    public List<RentEvent> getRents() {
        return rents;
    }
}
