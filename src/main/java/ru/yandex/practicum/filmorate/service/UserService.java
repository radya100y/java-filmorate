package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserDao> {

    @Autowired
    protected UserService(UserDao storage) {
        super(storage);
    }

    public List<User> addFriend(Integer userId, Integer otherUserId) {
        return storage.addFriend(userId, otherUserId);
    }

    public List<User> delFriend(Integer userId, Integer otherUserId) {
        return storage.delFriend(userId, otherUserId);
    }

    public List<User> getFriends(Integer userId) {
        return storage.getFriends(userId);
    }

    public List<User> getCommonFriends(Integer userId, Integer otherUserId) {
        return storage.getCommonFriends(userId, otherUserId);
    }
}
