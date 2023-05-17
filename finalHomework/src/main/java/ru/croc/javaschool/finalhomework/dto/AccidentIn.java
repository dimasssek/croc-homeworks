package ru.croc.javaschool.finalhomework.dto;

import ru.croc.javaschool.finalhomework.data.xml.LocalDateTimeConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Objects;

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

    /**
     * Создаёт {@link AccidentIn}. Нужен для Jaxb.
     */
    public AccidentIn() {
    }

    /**
     * Создаёт {@link AccidentIn}.
     *
     * @param timestamp временная точка
     * @param event     информация о дтп
     */
    public AccidentIn(LocalDateTime timestamp, String event) {
        this.timestamp = timestamp;
        this.event = event;
    }

    /**
     * @return временная точка
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return информация о дтп
     */
    public String getEvent() {
        return event;
    }

    /**
     * Сравнение объектов на равенство.
     *
     * @param o объект, с которым сравниваем.
     * @return {@code True}, если эквиваленты, {@code False} иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccidentIn accident = (AccidentIn) o;
        return Objects.equals(timestamp, accident.timestamp) && Objects.equals(event, accident.event);
    }

    /**
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(timestamp, event);
    }
}
