package ru.croc.javaschool.hornsandhooves.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Задача.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Уникальный идентификатор задачи.
     */
    private final UUID id;
    /**
     * Наименование задачи.
     */
    private String name;
    /**
     * Описание задачи.
     */
    private String description;
    /**
     * Исполнитель.
     */
    private String executor;
    /**
     * Статус.
     */
    private String status;

    /**
     * Создаёт {@link Task}.
     *
     * @param name        наименование задачи
     * @param description описание задачи
     * @param executor    исполнитель
     * @param status      статус
     */
    public Task(String name, String description, String executor, String status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutor() {
        return executor;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Код: " + getId() + ", Наименование: " + getName() + ", Описание: " + getDescription() +
                ", Исполнитель: " + getExecutor() + ", Статус: " + getStatus();
    }
}

