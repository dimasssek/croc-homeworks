package ru.croc.javaschool.software.enums;

/**
 * Категория транспортного средства.
 */
public enum VehicleCategory {
    /**
     * Автомобили.
     */
    AUTO("Автомобиль"),
    /**
     * Летательные транспортные средства.
     */
    AIRCRAFT("Летательное средство"),
    /**
     * Средства индивидуальной мобильности.
     */
    SIMPLE("Средство индивидуальной мобильности");

    /**
     * Название категории.
     */
    private String nameOfCategory;

    /**
     * Определяет название значения категории транспортного средства.
     *
     * @param nameOfCategory название категории транспортного средства.
     */
    VehicleCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    /**
     * Возвращает категорию транспортного средства.
     * @return категория транспортного средства.
     */
    public String getNameOfCategory() {
        return nameOfCategory;
    }
}
