package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.util.List;
@Service
public abstract class BaseService<T extends Entity, Y extends BaseStorage<T>> {
    private final Y storage;

    @Autowired
    protected BaseService(Y storage) {
        this.storage = storage;
    }

    public T create(T fact) {
        return storage.create(fact);
    }

    public T update(T fact) {
        return storage.update(fact);
    }

    public List<T> getAll() {
        return storage.getAll();
    }

    public T get(Integer id) {
        return storage.get(id);
    }
}
