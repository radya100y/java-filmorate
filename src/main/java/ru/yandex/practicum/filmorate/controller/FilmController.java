package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController extends BaseController<Film, InMemoryFilmStorage, FilmService> {

    protected FilmController(FilmService service) {
        super(service);
    }

    @PutMapping("/{filmId}/like/{userId}")
    public Film addLike(@PathVariable("filmId") Integer filmId, @PathVariable("userId") Integer userId) {
        return service.addLike(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public Film delLike(@PathVariable("filmId") Integer filmId, @PathVariable("userId") Integer userId) {
        return service.delLike(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopular(@RequestParam(defaultValue = "10", required = false) Integer count) {
        if (count <= 0) throw new ValidateException("Количество сообщений не может быть меньше 1");
        return service.getPopular(count);
    }
}
