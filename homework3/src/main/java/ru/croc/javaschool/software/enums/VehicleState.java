package ru.croc.javaschool.software.enums;

/**
 * Состояние транспортного средства.
 */
public enum VehicleState {
    /** Исправное транспортное средство. */
    SERVICEABLE("Исправное"),
    /** Неисправное транспортное средство. */
    FAULTY("Неисправное");
    /**
     * Состояние транспортного средства.
     */
    private String stateOfVehicle;

    /**
     * Определяет название значения состояния транспортного средства.
     * @param stateOfVehicle название состояния транспортного средства.
     */
    VehicleState(String stateOfVehicle) {
        this.stateOfVehicle = stateOfVehicle;
    }

    /**
     * Возрвращает состояние транспортного средства.
     * @return состояние транспортного средства.
     */
    public String getStateOfVehicle() {
        return stateOfVehicle;
    }

    /**
     * Изменяет состояние транспортного средства.
     * @param stateOfVehicle новое состояние транспортного средства.
     */
    public void setStateOfVehicle(String stateOfVehicle) {
        this.stateOfVehicle = stateOfVehicle;
    }
}
