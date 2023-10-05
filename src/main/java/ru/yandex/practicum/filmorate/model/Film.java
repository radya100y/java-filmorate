package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.validator.MinDateFilm;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Film extends Entity {

    @NotNull
    @NotBlank
    private String name;

    @Size(max = 200)
    private String description;

    @MinDateFilm
    private LocalDate releaseDate;

    @Min(1)
    @NotNull
    private int duration;

    private int rate;
}
