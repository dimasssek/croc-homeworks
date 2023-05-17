package ru.croc.javaschool.finalhomework.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Трафики.
 */
@XmlRootElement(name = "traffics")
public class TrafficListIn {
    /**
     * Список трафиков.
     */
    @XmlElement(name = "traffic")
    private List<TrafficIn> traffics;

    /**
     * Создаёт {@link TrafficListIn}. Нужен для Jaxb.
     */
    public TrafficListIn() {
    }

    /**
     * Создаёт {@link TrafficListIn}.
     *
     * @param traffics список трафиков
     */
    public TrafficListIn(List<TrafficIn> traffics) {
        this.traffics = traffics;
    }

    /**
     * @return список трафиков
     */
    public List<TrafficIn> getTraffics() {
        return traffics;
    }
}
