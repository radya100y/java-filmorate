package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Entity;
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
        return jdbcTemplate.query(sqlQuery, this::mapRowToGenre);
    }

    @Override
    public Genre get(Integer id) {
        sqlQuery = "select id, name from genre where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToGenre, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Рейтинг " + id + " не найден");
        }
    }

    public List<Entity> addFilmGenres(Integer filmId, List<Entity> genres) {
        if (genres == null) return null;
        sqlQuery = "merge into film_genre key(film_id, genre_id) values (?, ?)";
        for(Entity genre : genres) {
            jdbcTemplate.update(sqlQuery, filmId, genre.getId());
        }
        return getFilmGenres(filmId);
    }

    public List<Entity> getFilmGenres(Integer filmId) {
        sqlQuery = "select genre_id as id from film_genre where film_id = ?";
        return jdbcTemplate.query(sqlQuery, this::mapRowToEntity, filmId);
    }

    public void delFilmGenres(Integer filmId) {
        sqlQuery = "delete film_genre where film_id = " + filmId.toString();
        jdbcTemplate.execute(sqlQuery);
    }

    private Genre mapRowToGenre(ResultSet resultSet, int rowNum) throws SQLException {
        return new Genre(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

    private Entity mapRowToEntity(ResultSet resultSet, int rowNum) throws SQLException {
        return new Entity(
                resultSet.getInt("id")
        );
    }
}
