package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User, InMemoryUserStorage> {

    @Autowired
    protected UserService(InMemoryUserStorage storage) {
        super(storage);
    }

    public User addFriend(Integer userId, Integer otherUserId) {
        User user = this.get(userId);
        User otherUser = this.get(otherUserId);
        user.getFriends().add(otherUser.getId());
        otherUser.getFriends().add(user.getId());
        this.update(user);
        this.update(otherUser);
        return user;
    }

    public User delFriend(Integer userId, Integer otherUserId) {
        User user = this.get(userId);
        User otherUser = this.get(otherUserId);
        user.getFriends().remove(this.get(otherUserId).getId());
        otherUser.getFriends().remove(this.get(userId).getId());
        this.update(user);
        this.update(otherUser);
        return user;
    }

    public List<User> getFriends(Integer userId) {
        Set<Integer> friends = this.get(userId).getFriends();
        return friends.stream()
                .map(this::get)
                .collect(Collectors.toList());
    }

    public List<User> getCommonFriends(Integer userId, Integer otherUserId) {
        Set<Integer> friends = this.get(userId).getFriends();
        Set<Integer> otherFriends = this.get(otherUserId).getFriends();
        return friends.stream()
                .filter(otherFriends::contains)
                .map(this::get)
                .collect(Collectors.toList());
    }
}
