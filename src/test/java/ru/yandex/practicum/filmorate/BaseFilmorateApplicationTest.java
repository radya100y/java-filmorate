package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.BaseController;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.model.Film;

import static org.junit.jupiter.api.Assertions.assertThrows;
public abstract class BaseFilmorateApplicationTest<T extends BaseController, E extends Entity> {
    public T controller;
    public E entity;

    @Test
    void failAddEmpty() {
		assertThrows(ValidateException.class, () -> controller.create(entity));
    }
}
