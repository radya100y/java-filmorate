package ru.yandex.practicum.filmorate.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

@Validated
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User> {
    @Override
    public void validate(User user) {
    }

}
