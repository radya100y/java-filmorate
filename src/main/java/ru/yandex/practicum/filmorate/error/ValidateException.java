package ru.yandex.practicum.filmorate.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateException extends RuntimeException {
    public ValidateException(String msg) {
        super(msg);
        log.warn(msg);
    }
}
