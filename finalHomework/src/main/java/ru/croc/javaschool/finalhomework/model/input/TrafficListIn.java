package ru.croc.javaschool.finalhomework.model.input;

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

    public TrafficListIn() {
    }

    public TrafficListIn(List<TrafficIn> traffics) {
        this.traffics = traffics;
    }

    public List<TrafficIn> getTraffics() {
        return traffics;
    }
}
