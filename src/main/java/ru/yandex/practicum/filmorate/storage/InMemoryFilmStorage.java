package ru.yandex.practicum.filmorate.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryFilmStorage implements BaseStorage<Film> {

    private final HashMap<Integer, Film> kv;
    private int id = 0;

    @Autowired
    public InMemoryFilmStorage(HashMap<Integer, Film> kv) {
        this.kv = kv;
    }

    @Override
    public void validate(Film film) {
    }

    @Override
    public Film create(Film film) {
        int id = ++this.id;
        film.setId(id);
        kv.put(id, film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (!kv.containsKey(film.getId())) {
            throw new NotFoundException("Фильм с идентификатором " + film.getId() + " не найден");
        }
        kv.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(kv.values());
    }

    @Override
    public Film get(Integer id) {
        if (!kv.containsKey(id)) {
            throw new NotFoundException("Фильм с идентификатором " + id + " не найден");
        }
        return kv.get(id);
    }
}
