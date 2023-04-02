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
     * Выполняет аренду транспортного средства.
     *
     * @param rent аренда транспортного средства.
     */
    public void rentVehicle(RentEvent rent) {
        if (this.isFree(rent.getStartOfRent(), rent.getDayOfEndRent())) {
            rents.add(rent);
        } else {
            System.out.println("Бронирование данного транспорта не может быть выполнено");
        }
    }

    /**
     * Выполняет вывод всех забронированных дат для данного автомобиля.
     */
    public void printDatesOfRent() {
        for (RentEvent rent : rents) {
            System.out.println(rent.toString() + "\n");
        }
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
    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public String getNameOfVehicle() {
        return nameOfVehicle;
    }

    public List<RentEvent> getRents() {
        return rents;
    }

    /**
     * Изменяет состояние на "Неисправное"
     */
    public void setFaultyState() {
        this.vehicleState = VehicleState.FAULTY;
    }

    /**
     * Проверка является ли транспорт свободным на указанную дату.
     *
     * @return false если забронировано, true если свободно.
     */
    public boolean isFree(LocalDate date) {
        for (RentEvent rent : rents) {
            if (date.compareTo(rent.getDayOfEndRent()) * date.compareTo(rent.getStartOfRent()) == 1)
                return true;
        }
        return false;
    }

    /**
     * Проверка является ли транспорт свободным на указанную дату.
     * Используется метод временной интерсекции(проверка наличия пересечения двух отрезков дат).
     *
     * @return true если забронировано, false если свободно.
     */
    public boolean isFree(LocalDate startDate, LocalDate endDate) {
        for (RentEvent rent : rents) {
            if (rent.getStartOfRent().compareTo(endDate) > 0 || startDate.compareTo(rent.getDayOfEndRent()) > 0)
                return false;
        }
        return true;
    }
}
