package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryUserStorage implements BaseStorage<User> {

    private final HashMap<Integer, User> kv = new HashMap<>();
    private int id = 0;

    @Override
    public void validate(User user) {
        if (user.getName() == null) user.setName(user.getLogin());
    }

    @Override
    public User create(User user) {
        this.validate(user);
        int id = ++this.id;
        user.setId(id);
        kv.put(id, user);
        return user;
    }

    @Override
    public User update(User user) {
        if (!kv.containsKey(user.getId())) {
            throw new NotFoundException(user.getId());
        }
        kv.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(kv.values());
    }
}
