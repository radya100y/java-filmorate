package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.dao.impl.GenreDao;

@Service
public class GenreService extends BaseService<Genre, GenreDao> {

    protected GenreService(GenreDao storage) {
        super(storage);
    }
}
