package ru.croc.javaschool.finalhomework.model.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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

    public AccidentListIn() {
    }

    public AccidentListIn(List<AccidentIn> accidents) {
        this.accidents = accidents;
    }

    public List<AccidentIn> getAccidents() {
        return accidents;
    }
}
