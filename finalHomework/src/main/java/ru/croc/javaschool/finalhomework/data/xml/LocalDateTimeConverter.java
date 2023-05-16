package ru.croc.javaschool.finalhomework.data.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Преобразователь {@link LocalDateTime} в строку формата yyyy-MM-dd'T'HH:mm:ss и обратно.
 */
public class LocalDateTimeConverter extends XmlAdapter<String, LocalDateTime> {
    /**
     * Формат даты и времени для преобразования в строку.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Преобразует строку в дату и время.
     *
     * @param s строковое представление даты и времени
     * @return дата и время.
     * @throws Exception если произошла ошибка преобразования
     */
    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s, formatter);
    }

    /**
     * Преобразует дату и время в строку.
     *
     * @param time дата и время.
     * @return строковое представление даты и времени
     * @throws Exception если произошла ошибка преобразования.
     */
    @Override
    public String marshal(LocalDateTime time) throws Exception {
        return time.format(formatter);
    }
}
