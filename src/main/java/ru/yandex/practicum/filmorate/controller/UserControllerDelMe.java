package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.UserDelMe;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/users")
public class UserControllerDelMe {
    List<UserDelMe> users = new ArrayList<>();
    private int seqUser = 0;
    private static final Logger log = LoggerFactory.getLogger(UserDelMe.class);

//    @GetMapping
    public List<UserDelMe> users() {
        return users;
    }

//    @PostMapping
    public UserDelMe create(@Valid @RequestBody UserDelMe user) {
        user.setId(++seqUser);
        users.add(user);
        log.info("Создан пользователь " + user);
        return user;
    }

//    @PutMapping
    public UserDelMe change(@Valid @RequestBody UserDelMe user) {
        for(UserDelMe existingUser : users) {
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
