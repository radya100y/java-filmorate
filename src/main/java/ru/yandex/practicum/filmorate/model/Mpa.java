package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Mpa extends Entity {
    private String name;

    public Mpa(int id, String name) {
        super(id);
        this.name = name;
    }

}
