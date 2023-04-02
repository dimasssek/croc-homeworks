package ru.croc.javaschool.software.events;

import ru.croc.javaschool.software.vehicle.Vehicle;

/**
 * Обеспечивает регистрацию и списание транспортных средств.
 */
public interface AccountingOperations {
    /**
     * Метод для регистрации нового транспортного средства.
     */
    void registration(Vehicle vehicle);

    /**
     * Метод для списания неисправного транспортного средства.
     */
    void remove(Vehicle vehicle);
}
