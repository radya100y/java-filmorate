package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Genre extends Entity {
    private String name;

    public Genre(int id, String name) {
        super(id);
        this.name = name;
    }
}
