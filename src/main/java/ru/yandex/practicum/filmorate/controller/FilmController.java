package ru.yandex.practicum.filmorate.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@Data
@RestController
@RequestMapping("/films")
public class FilmController extends BaseController<Film> {
    @Override
    public void validate(Film film) {
        validateName(film);
        validateDescription(film);
        validateDate(film);
        validateDuration(film);
    }
    private void validateName(Film film) {
        if (film.getName() == null) throw new ValidateException("Film name is blank");
        else if (film.getName().isBlank()) throw new ValidateException("Film name is blank");
    }
    private void validateDescription(Film film) {
        if (film.getDescription() == null) return;
        if (film.getDescription().length() > 200)
            throw new ValidateException("Film description is more than 200 chars");
    }
    private void validateDate(Film film) {
        if (film.getReleaseDate() == null) return;
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28)))
            throw new ValidateException("Film release date is lower then 1895 year");
    }
    private void validateDuration(Film film) {
        if (film.getDuration() < 1)
            throw new ValidateException("Film duration is lower then 1");
    }
}
