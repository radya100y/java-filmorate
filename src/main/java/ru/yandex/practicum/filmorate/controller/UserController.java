package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.BaseService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, InMemoryUserStorage, UserService> {

    protected UserController(UserService service) {
        super(service);
    }
}
