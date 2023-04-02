package ru.croc.javaschool.software.events;

import java.time.LocalDate;

/**
 * Обеспечивает отчётность по свободным транспортным средствам в определенные даты,
 * отчётность по конкретной дате и ведёт учет арендованных транспортных средств в определенные даты.
 */
public interface Reportable {
    /**
     * Создаёт сводный отчёт для заданной даты, предоставляющий статистику
     * по категориям и количеству занятых транспортных средств.
     *
     * @param date заданная дата.
     */
    void createReport(LocalDate date);

    /**
     * Поиск свободных транспортных средств по категориям
     *
     * @param dateOfStartRent Дата начала аренды.
     * @param dateOfEndRent   Дата окончания аренды.
     */
     void toCategories(LocalDate dateOfStartRent, LocalDate dateOfEndRent);
    /**
     * Учет арендованных на заданные даты транспортных средств.
     *
     * @param dateOfStartRent Дата начала аренды.
     * @param dateOfEndRent   Дата окончания аренды.
     */
    void trackRentedVehicles(LocalDate dateOfStartRent, LocalDate dateOfEndRent);
}
