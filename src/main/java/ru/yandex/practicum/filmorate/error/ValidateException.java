package ru.yandex.practicum.filmorate.error;

public class ValidateException extends RuntimeException {
    public ValidateException(String msg) {
        super(msg);
    }
}
