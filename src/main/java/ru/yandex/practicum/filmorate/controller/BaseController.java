package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public abstract class BaseController<T extends Entity> {
    private final HashMap<Integer, T> kv = new HashMap<>();
    private int id = 0;
    protected abstract void validate(T fact);
    @PostMapping
    public T create(@RequestBody final T fact) {
        validate(fact);
        int id = ++this.id;
        fact.setId(id);
        kv.put(id, fact);
        return fact;
    }
    @PutMapping
    public T update(@RequestBody final T fact) {
        if (!kv.containsKey(fact.getId())) throw new ValidateException("key not found");
        validate(fact);
        kv.put(fact.getId(), fact);
        return fact;
    }
    @GetMapping
    public List<T> get() {
        return new ArrayList<>(kv.values());
    }
}
