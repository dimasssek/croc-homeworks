package ru.croc.javaschool.finalhomework.model.entity;

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

    private Double coefficientWorkload;
    /**
     * Информация о происшествии.
     */
    private String info;

    public AccidentOut() {
    }

    public AccidentOut(UUID id, LocalDateTime timestamp, Double coefficientWorkload, String info) {
        this.id = id;
        this.timestamp = timestamp;
        this.coefficientWorkload = coefficientWorkload;
        this.info = info;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getCoefficientWorkload() {
        return coefficientWorkload;
    }

    public String getInfo() {
        return info;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setCoefficientWorkload(Double coefficientWorkload) {
        this.coefficientWorkload = coefficientWorkload;
    }

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

    @Override
    public String toString() {
        return "Произошло ДТП в " +
                timestamp + ". Коэффициент загруженности в этот момент: " +
                coefficientWorkload + ". Информация о ДТП: " +
                info + ". \n";
    }
}
