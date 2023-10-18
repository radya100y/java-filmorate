package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.stream.Collectors;

import java.util.Set;

@Service
public class FilmService extends BaseService<Film, InMemoryFilmStorage> {

    @Autowired
    protected FilmService(InMemoryFilmStorage storage) {
        super(storage);
    }

    public Film addLike (Integer filmId, Integer userId) {
        Film film = get(filmId);
        film.getLikeUsers().add(userId);
        update(film);
        return film;
    }

    public Film delLike(Integer filmId, Integer userId) {
        Film film = get(filmId);
        film.getLikeUsers().remove(userId);
        update(film);
        return film;
    }

    public Set<Integer> getPopular(Integer size) {
        return getAll().stream()
                .sorted(this::compare)
                .limit(size)
                .map(Entity::getId)
                .collect(Collectors.toSet());
    }

    private int compare(Film x, Film y) {
        return -1 * Integer.compare(x.getLikeUsers().size(), y.getLikeUsers().size());
    }
}
