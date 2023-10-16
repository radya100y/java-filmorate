package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryFilmStorage implements BaseStorage<Film> {

    private final HashMap<Integer, Film> kv = new HashMap<>();
    private int id = 0;

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
            throw new NotFoundException(film.getId());
        }
        kv.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(kv.values());
    }
}
