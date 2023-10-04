package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Setter
@Getter
public class Film extends Entity {
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;
    private int rate;
}
