package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.model.Entity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseStorageDao<T extends Entity> {

    T create(T fact) throws SQLException;

    T update(T fact);

    List<T> getAll();

    Optional<T> get(Integer id);
}
