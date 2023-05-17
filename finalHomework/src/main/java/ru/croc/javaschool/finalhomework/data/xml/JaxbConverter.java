package ru.croc.javaschool.finalhomework.data.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

/**
 * Конвертер для чтения xml.
 */
public class JaxbConverter {


    /**
     * Десериализация xml в объект.
     *
     * @param xml  xml
     * @param type тип объекта
     * @param <T>  тип
     * @return объект
     */
    public <T> T fromXml(String xml, Class<T> type) {
        try {
            return createXmlMapper().readValue(xml, type);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Создаём настроенный mapper JAXB.
     *
     * @return mapper
     */
    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.setDefaultUseWrapper(false);
        return mapper;
    }
}
