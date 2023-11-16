package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;
import ru.yandex.practicum.filmorate.storage.dao.impl.MpaDao;

@RestController
@RequestMapping("/mpa")
@Slf4j
public class MpaController extends BaseController<Mpa, MpaDao, MpaService> {

    protected MpaController(MpaService service) {
        super(service);
    }

}
