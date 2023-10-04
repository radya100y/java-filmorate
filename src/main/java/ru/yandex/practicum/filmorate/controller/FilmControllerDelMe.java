package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.FilmDelMe;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/films")
public class FilmControllerDelMe {
    List<FilmDelMe> filmDelMes = new ArrayList<>();
    private int seqFilm = 0;
    private static final Logger log = LoggerFactory.getLogger(FilmDelMe.class);

//    @GetMapping
    public List<FilmDelMe> films() {
        return filmDelMes;
    }
//    @PostMapping
    public FilmDelMe create(@Valid @RequestBody FilmDelMe filmDelMe) {
        filmDelMe.setId(++seqFilm);
        filmDelMes.add(filmDelMe);
        log.info("Создан фильм " + filmDelMe);
        return filmDelMe;
    }
//    @PutMapping
    public FilmDelMe change(@Valid @RequestBody FilmDelMe filmDelMe) {
        for (FilmDelMe existingFilmDelMe : filmDelMes) {
            if (existingFilmDelMe.getId() == filmDelMe.getId()) {
                filmDelMes.remove(existingFilmDelMe);
                filmDelMes.add(filmDelMe);
                log.info("Фильм " + existingFilmDelMe + " обновлен");
                return filmDelMe;
            }
        }
        log.warn("Обновление фильма " + filmDelMe + " не удалось");
        throw new ValidateException("Фильм не найден");
    }
/*    public boolean validate(@Valid Film film) {

        return false;
    }*/
}
