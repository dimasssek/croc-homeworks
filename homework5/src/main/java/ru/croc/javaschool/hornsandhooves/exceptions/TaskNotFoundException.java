package ru.croc.javaschool.hornsandhooves.exceptions;

/**
 * Задача не найдена.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
