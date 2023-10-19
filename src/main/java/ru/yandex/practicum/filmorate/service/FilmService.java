package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService extends BaseService<Film, InMemoryFilmStorage> {

    InMemoryUserStorage userStorage;

    @Autowired
    protected FilmService(InMemoryFilmStorage storage, InMemoryUserStorage userStorage) {
        super(storage);
        this.userStorage = userStorage;
    }

    public Film addLike(Integer filmId, Integer userId) {
        Film film = get(filmId);
        User user = userStorage.get(userId);
        film.getLikeUsers().add(user.getId());
        update(film);
        return film;
    }

    public Film delLike(Integer filmId, Integer userId) {
        Film film = get(filmId);
        User user = userStorage.get(userId);
        film.getLikeUsers().remove(user.getId());
        update(film);
        return film;
    }

    public List<Film> getPopular(Integer count) {
        return getAll().stream()
                .sorted(this::compare)
                .limit(count)
                .collect(Collectors.toList());
    }

    private int compare(Film x, Film y) {
        return -1 * Integer.compare(x.getLikeUsers().size(), y.getLikeUsers().size());
    }
}
