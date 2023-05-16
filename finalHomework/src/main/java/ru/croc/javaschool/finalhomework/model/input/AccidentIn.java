package ru.croc.javaschool.finalhomework.model.input;

import ru.croc.javaschool.finalhomework.data.xml.LocalDateTimeConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * ДТП, поступающее из xml на вход.
 */
public class AccidentIn {
    /**
     * Временная точка.
     */
    @XmlJavaTypeAdapter(LocalDateTimeConverter.class)
    @XmlElement(name = "timestamp")
    private LocalDateTime timestamp;
    /**
     * Информация о ДТП.
     */
    @XmlElement(name = "event")
    private String event;

    public AccidentIn() {
    }

    public AccidentIn(LocalDateTime timestamp, String event) {
        this.timestamp = timestamp;
        this.event = event;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getEvent() {
        return event;
    }
}
