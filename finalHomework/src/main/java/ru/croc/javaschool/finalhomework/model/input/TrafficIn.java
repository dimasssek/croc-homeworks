package ru.croc.javaschool.finalhomework.model.input;

import ru.croc.javaschool.finalhomework.data.xml.LocalDateTimeConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Трафик, поступающий из xml на вход.
 */
public class TrafficIn {
    /**
     * Временная точка.
     */
    @XmlJavaTypeAdapter(LocalDateTimeConverter.class)
    @XmlElement(name = "timestamp")
    private LocalDateTime timestamp;

    /**
     * Коэфициент загруженности.
     */
    @XmlElement(name = "value")
    private Double value;

    public TrafficIn() {
    }

    public TrafficIn(LocalDateTime timestamp, Double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getValue() {
        return value;
    }
}
