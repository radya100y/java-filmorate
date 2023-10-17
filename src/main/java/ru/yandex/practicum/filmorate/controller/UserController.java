package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.BaseService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, InMemoryUserStorage, UserService> {

    protected UserController(UserService service) {
        super(service);
    }

    @PutMapping("/{userId}/friends/{otherUserId}")
    public User addFriend(@PathVariable("userId") Integer userId, @PathVariable("otherUserId") Integer otherUserId) {
        return service.addFriend(userId, otherUserId);
    }

    @DeleteMapping("/{userId}/friends/{otherUserId}")
    public User delFriend(@PathVariable("userId") Integer userId, @PathVariable("otherUserId") Integer otherUserId) {
        return service.delFriend(userId, otherUserId);
    }

    @GetMapping("/{userId}/friends")
    public Set<Integer> getFriends(@PathVariable("userId") Integer userId) {
        return service.get(userId).getFriends();
    }

    @GetMapping("/{userId}/friends/common/{otherUserId}")
    public Set<Integer> getCommonFriends(@PathVariable("userId") Integer userId,
                                         @PathVariable("otherUserId") Integer otherUserId) {
        return service.getCommonFriends(userId, otherUserId);
    }
}
