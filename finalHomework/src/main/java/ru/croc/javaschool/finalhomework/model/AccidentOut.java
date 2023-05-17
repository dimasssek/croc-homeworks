package ru.croc.javaschool.finalhomework.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Дорожно-транспортное происшествие(ДТП). Модель для БД.
 */
public class AccidentOut {
    /**
     * Уникальный идентификатор.
     */
    private UUID id;

    /**
     * Временная точка.
     */

    private LocalDateTime timestamp;

    /**
     * Коэффициент загруженности дорог.
     */

    private BigDecimal coefficientWorkload;
    /**
     * Информация о происшествии.
     */
    private String info;
    /**
     * Создаёт {@link AccidentOut}.
     */
    public AccidentOut() {
    }

    /**
     * Создаёт {@link AccidentOut}.
     * @param id уникальный идентификатор
     * @param timestamp временная точка
     * @param coefficientWorkload коэффициент загруженности дорог
     * @param info информация о дтп
     */
    public AccidentOut(UUID id, LocalDateTime timestamp, BigDecimal coefficientWorkload, String info) {
        this.id = id;
        this.timestamp = timestamp;
        this.coefficientWorkload = coefficientWorkload;
        this.info = info;
    }

    /**
     *
     * @return временная точка
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @return коэффициент загруженности дорог
     */
    public BigDecimal getCoefficientWorkload() {
        return coefficientWorkload;
    }

    /**
     *
     * @return информация о дтп
     */
    public String getInfo() {
        return info;
    }

    /**
     * Сеттер. Используется для инициализации объекта без id.
     * @param timestamp временная точка
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Сеттер. Используется для инициализации объекта без id.
     * @param coefficientWorkload коэффициент загруженности дорог
     */
    public void setCoefficientWorkload(BigDecimal coefficientWorkload) {
        this.coefficientWorkload = coefficientWorkload;
    }

    /**
     * Сеттер. Используется для инициализации объекта без id.
     * @param info информация о дтп
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Сравнение ДТП без айдишников.
     * @param o ДТП
     * @return {@code True}, если эквиваленты, {@code False} иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccidentOut accident = (AccidentOut) o;
        return Objects.equals(timestamp, accident.timestamp) &&
                Objects.equals(coefficientWorkload, accident.coefficientWorkload) &&
                Objects.equals(info, accident.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, coefficientWorkload, info);
    }

    /**
     * @return строковое представление ДТП
     */
    @Override
    public String toString() {
        return "Произошло ДТП в " +
                timestamp + ". Коэффициент загруженности в этот момент: " +
                coefficientWorkload + ". Информация о ДТП: " +
                info + ". \n";
    }
}
