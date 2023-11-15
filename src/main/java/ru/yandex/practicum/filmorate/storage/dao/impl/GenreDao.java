package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Primary
public class GenreDao implements BaseStorage<Genre> {

    private final JdbcTemplate jdbcTemplate;
    private String sqlQuery;

    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void validate(Genre fact) {
    }

    @Override
    public Genre create(Genre fact) {
        return null;
    }

    @Override
    public Genre update(Genre fact) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        sqlQuery = "select id, name from genre";
        return jdbcTemplate.query(sqlQuery, new GenreMapRow());
    }

    @Override
    public Genre get(Integer id) {
        sqlQuery = "select id, name from genre where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new GenreMapRow());
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Рейтинг " + id + " не найден");
        }
    }

    public List<Genre> addFilmGenres(Integer filmId, List<Genre> genres) {
        if (genres == null) return null;
        sqlQuery = "merge into film_genre key(film_id, genre_id) values (?, ?)";
        for (Genre genre : genres) {
            jdbcTemplate.update(sqlQuery, filmId, genre.getId());
        }
        return getFilmGenres(filmId);
    }

    public List<Genre> getFilmGenres(Integer filmId) {
        sqlQuery = "select id, name from genre where id in (select genre_id from film_genre where film_id = ?)";
        return jdbcTemplate.query(sqlQuery, new GenreMapRow(), filmId);
    }

    public void delFilmGenres(Integer filmId) {
        sqlQuery = "delete film_genre where film_id = " + filmId.toString();
        jdbcTemplate.execute(sqlQuery);
    }

    private static class GenreMapRow implements RowMapper<Genre> {

        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getInt("id");
            return new Genre(
                    id,
                    rs.getString("name")
            );
        }
    }
}
