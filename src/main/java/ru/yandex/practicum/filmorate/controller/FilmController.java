package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.BaseService;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

@RestController
@RequestMapping("/films")
public class FilmController extends BaseController<Film, InMemoryFilmStorage, FilmService> {

    protected FilmController(FilmService service) {
        super(service);
    }
}
