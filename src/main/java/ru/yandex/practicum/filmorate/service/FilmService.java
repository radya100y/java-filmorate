package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.dao.impl.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.MpaDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.util.List;

@Service
public class FilmService extends BaseService<Film, FilmDao> {

    private final UserDao userStorage;
    private final GenreDao genreStorage;
    private final MpaDao mpaStorage;

    @Autowired
    protected FilmService(FilmDao storage, UserDao userStorage, GenreDao genreStorage, MpaDao mpaStorage) {
        super(storage);
        this.userStorage = userStorage;
        this.genreStorage = genreStorage;
        this.mpaStorage = mpaStorage;
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
        List<Film> lf = storage.getPopular(count);
        for(Film film : lf) {
            film.setMpa(mpaStorage.get(film.getId()));
            film.setGenres(genreStorage.getFilmGenres(film.getId()));
        }
        return lf;
    }

    @Override
    public Film create(Film fact) {
        Film film = super.create(fact);
        film.setMpa(mpaStorage.get(fact.getMpa().getId()));
        film.setGenres(genreStorage.addFilmGenres(film.getId(), fact.getGenres()));
        return film;
    }

    @Override
    public Film update(Film fact) {
        Film film = super.update(fact);
        film.setMpa(mpaStorage.get(fact.getMpa().getId()));
        genreStorage.delFilmGenres(film.getId());
        film.setGenres(genreStorage.addFilmGenres(film.getId(), fact.getGenres()));
        return film;
    }

    @Override
    public List<Film> getAll() {
        List<Film> lf = super.getAll();
        for(Film film : lf) {
            film.setMpa(mpaStorage.get(film.getId()));
            film.setGenres(genreStorage.getFilmGenres(film.getId()));
        }
        return lf;
    }

    @Override
    public Film get(Integer id) {
        Film film = super.get(id);
        film.setMpa(mpaStorage.get(id));
        film.setGenres(genreStorage.getFilmGenres(id));
        return film;
    }

}
