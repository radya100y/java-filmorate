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

/*    public User delFriend(Integer userId, Integer otherUserId) {
        User user = this.get(userId);
        User otherUser = this.get(otherUserId);
        user.getFriends().remove(this.get(otherUserId).getId());
        otherUser.getFriends().remove(this.get(userId).getId());
        this.update(user);
        this.update(otherUser);
        return user;
    }*/

    public List<User> getFriends(Integer userId) {
        return storage.getFriends(userId);
    }
/*
    public List<User> getCommonFriends(Integer userId, Integer otherUserId) {
        Set<Integer> friends = this.get(userId).getFriends();
        Set<Integer> otherFriends = this.get(otherUserId).getFriends();
        return friends.stream()
                .filter(otherFriends::contains)
                .map(this::get)
                .collect(Collectors.toList());
    }*/
}
