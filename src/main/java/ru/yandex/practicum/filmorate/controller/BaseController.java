package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Entity;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public abstract class BaseController<T extends Entity> {
    private final HashMap<Integer, T> kv = new HashMap<>();
    private int id = 0;
    protected abstract void validate(T fact);

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T fact) {
        int id = ++this.id;
        fact.setId(id);
        kv.put(id, fact);
        log.info("Добавлена новая запись {}", fact);
        return ResponseEntity.ok(fact);
    }

    @PutMapping
    public ResponseEntity<T> update(@Valid @RequestBody T fact) {
        if (!kv.containsKey(fact.getId())) {
            log.warn("Неудачная попытка обновления пользователя {}", fact);
            return ResponseEntity.badRequest().build();
        }
        kv.put(fact.getId(), fact);
        log.info("Запись {} обновлена", fact);
        return ResponseEntity.ok(fact);
    }

    @GetMapping
    public List<T> get() {
        return new ArrayList<>(kv.values());
    }
}
