package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.dao.impl.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService extends BaseService<Film, FilmDao> {

    private final UserDao userStorage;

    @Autowired
    protected FilmService(FilmDao storage, UserDao userStorage) {
        super(storage);
        this.userStorage = userStorage;
    }

    public Film addLike(Integer filmId, Integer userId) {
        get(filmId);
        userStorage.get(userId);
        return storage.addLike(filmId, userId);
    }

    public Film delLike(Integer filmId, Integer userId) {
        get(filmId);
        userStorage.get(userId);
        return storage.delLike(filmId, userId);
    }

    public List<Film> getPopular(Integer count) {
        return storage.getPopular(count);
    }

    private int compare(Film x, Film y) {
        return -Integer.compare(x.getLikeUsers().size(), y.getLikeUsers().size());
    }
}
