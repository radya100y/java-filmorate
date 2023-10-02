package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    List<Film> films = new ArrayList<>();
    private int seqFilm = 0;
    private static final Logger log = LoggerFactory.getLogger(Film.class);

    @GetMapping
    public List<Film> films() {
        return films;
    }
    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        film.setId(++seqFilm);
        films.add(film);
        log.info("Создан фильм " + film);
        return film;
    }
    @PutMapping
    public Film change(@Valid @RequestBody Film film) {
        for (Film existingFilm : films) {
            if (existingFilm.getId() == film.getId()) {
                films.remove(existingFilm);
                films.add(film);
                log.info("Фильм " + existingFilm + " обновлен");
                return film;
            }
        }
        log.warn("Обновление фильма " + film + " не удалось");
        throw new ValidateException("Фильм не найден");
    }
}
