package ru.yandex.exceptions;

public class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(final String message, Throwable cause) {
        super(message, cause);
    }
}
