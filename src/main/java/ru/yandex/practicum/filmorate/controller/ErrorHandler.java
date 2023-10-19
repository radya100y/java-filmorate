package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.ErrorResponse;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidateException(final ValidateException exc) {
        return new ErrorResponse(
                exc.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidateArgumentException(final MethodArgumentNotValidException exc) {
        return new ErrorResponse(
                String.format("Ошибка с полем %s.", Objects.requireNonNull(exc.getFieldError()).getField())
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException exc) {
        return new ErrorResponse(
                exc.getParameter()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable exc) {
        return new ErrorResponse(
                "Произошла непредвиденная ошибка."
        );
    }
}
