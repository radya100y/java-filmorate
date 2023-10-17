package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

@Service
public class UserService extends BaseService<User, InMemoryUserStorage> {

    @Autowired
    protected UserService(InMemoryUserStorage storage) {
        super(storage);
    }
}
