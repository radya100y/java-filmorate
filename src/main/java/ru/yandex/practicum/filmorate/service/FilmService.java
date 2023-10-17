package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

@Service
public class FilmService extends BaseService<Film, InMemoryFilmStorage> {

    @Autowired
    protected FilmService(InMemoryFilmStorage storage) {
        super(storage);
    }
}
