package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    List<User> users = new ArrayList<>();
    private int seqUser = 0;
    private static final Logger log = LoggerFactory.getLogger(User.class);

    @GetMapping
    public List<User> users() {
        return users;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setId(++seqUser);
        users.add(user);
        log.info("Создан пользователь " + user);
        return user;
    }

    @PutMapping
    public User change(@Valid @RequestBody User user) {
        for(User existingUser : users) {
            if (existingUser.getId() == user.getId()) {
                users.remove(existingUser);
                users.add(user);
                log.info("Пользователь " + existingUser + " обновлен");
                return user;
            }
        }
        log.warn("Обновление пользователя" + user + " не удалось.");
        throw new ValidateException("Пользователь не найден");
    }
}
