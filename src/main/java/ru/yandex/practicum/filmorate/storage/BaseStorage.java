package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface BaseStorage<T extends Entity> {

    void validate(T fact);

    T create(T fact);

    T update(T fact);

    List<T> getAll();

    T get(Integer id);
}
