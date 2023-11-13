package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;
import ru.yandex.practicum.filmorate.storage.dao.impl.GenreDao;

@RestController
@RequestMapping("/genres")
@Slf4j
public class GenreController extends BaseController<Genre, GenreDao, GenreService> {

    protected GenreController(GenreService service) {
        super(service);
    }
}
