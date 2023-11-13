package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.validator.MinDateFilm;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Data
public class Film extends Entity {

    @NotBlank
    private String name;

    @Size(max = 200)
    private String description;

    @MinDateFilm(value = "1895-12-28")
    private LocalDate releaseDate;

    @Min(1)
    @NotNull
    private int duration;

    private int rate;

//    private Set<Integer> likeUsers = new HashSet<>();
    private Mpa mpa;
    private List<Genre> genres;

    public Film(int id, String name, String description, Date releaseDate, int duration, int rate, Mpa mpa, List<Genre> genres) {
        super(id);
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate.toLocalDate();
        this.duration = duration;
        this.rate = rate;
        this.mpa = mpa;
        this.genres = genres;
    }
}
