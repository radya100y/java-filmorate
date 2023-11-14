package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.dao.impl.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.MpaDao;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IntegrationFilmTest {

    private final JdbcTemplate jdbcTemplate;
    private Film newFilm = new Film(1, "name", "descr", Date.valueOf("2000-01-01"), 100,
            1, new Mpa(1, "G"), List.of(new Genre(1, "Комедия"),
            new Genre(2, "Драма")));

    private Film updFilm = new Film(1, "n_1", "d_1", Date.valueOf("2000-01-02"), 101,
            2, new Mpa(2, "PG"), List.of(new Genre(3, "Мультфильм"),
            new Genre(4, "Триллер")));

    private FilmDao filmDao;
    private MpaDao mpaDao;
    private GenreDao genreDao;

    @BeforeEach
    public void initTest() {
        mpaDao = new MpaDao(jdbcTemplate);
        genreDao = new GenreDao(jdbcTemplate);
        filmDao = new FilmDao(jdbcTemplate, mpaDao, genreDao);
        filmDao.create(newFilm);
    }

    @Test
    public void validCreate() {

        filmDao.get(1).setGenres(genreDao.addFilmGenres(1, newFilm.getGenres()));
        assertThat(filmDao.get(1))
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(newFilm);
    }

    @Test
    public void validUpdate() {

        filmDao.update(updFilm);

        filmDao.get(1).setGenres(genreDao.addFilmGenres(1, updFilm.getGenres()));

        assertThat(filmDao.get(1))
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(updFilm);
    }
}
