package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.service.BaseService;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import javax.validation.Valid;
import java.util.List;

@Slf4j
public abstract class BaseController<T extends Entity, Y extends BaseStorage<T>, U extends BaseService<T, Y>> {

    public final U service;

    protected BaseController(U service) {
        this.service = service;
    }

    @PostMapping
    public T create(@Valid @RequestBody T fact) {
        return service.create(fact);
    }

    @PutMapping
    public T update(@Valid @RequestBody T fact) {
        return service.update(fact);
    }

    @GetMapping
    public List<T> getAll() {
        return service.getAll();
    }

    @GetMapping("/{factId}")
    public T get(@PathVariable("factId") Integer factId) {
        return service.get(factId);
    }
}
