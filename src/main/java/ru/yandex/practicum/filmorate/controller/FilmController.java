package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.IncorrectParameterException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.BaseService;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Set;

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
    public Set<Integer> getPopular(@RequestParam(defaultValue = "10", required = false) Integer size) {
        if (size <= 0) throw new IncorrectParameterException("size");
        return service.getPopular(size);
    }
}
