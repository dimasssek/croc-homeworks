package ru.croc.javaschool.hornsandhooves.exceptions;

/**
 * Задача не найдена.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Задача не найдена.");
    }
}
