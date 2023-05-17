package ru.croc.javaschool.finalhomework.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Дорожно-транспортные происшествия.
 */
@XmlRootElement(name = "accidents")
public class AccidentListIn {
    /**
     * Список ДТП.
     */
    @XmlElement(name = "accident")
    private List<AccidentIn> accidents;

    /**
     * Создаёт {@link AccidentListIn}. Нужен для Jaxb.
     */
    public AccidentListIn() {
    }

    /**
     * Создаёт {@link AccidentListIn}.
     *
     * @param accidents список дтп
     */
    public AccidentListIn(List<AccidentIn> accidents) {
        this.accidents = accidents;
    }

    /**
     * @return список дтп
     */
    public List<AccidentIn> getAccidents() {
        return accidents;
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
        AccidentListIn that = (AccidentListIn) o;
        return Objects.equals(accidents, that.accidents);
    }

    /**
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(accidents);
    }
}
