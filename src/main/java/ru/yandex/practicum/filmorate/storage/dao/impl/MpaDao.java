package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Primary
public class MpaDao implements BaseStorage<Mpa> {

    private final JdbcTemplate jdbcTemplate;
    private String sqlQuery;

    public MpaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void validate(Mpa fact) {
    }

    @Override
    public Mpa create(Mpa fact) {
        return null;
    }

    @Override
    public Mpa update(Mpa fact) {
        return null;
    }

    @Override
    public List<Mpa> getAll() {
        sqlQuery = "select id, name from mpa";
        return jdbcTemplate.query(sqlQuery, this::mapRowToMpa);
    }

    @Override
    public Mpa get(Integer id) {
        sqlQuery = "select id, name from mpa where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToMpa, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Рейтинг " + id + " не найден");
        }
    }

    private Mpa mapRowToMpa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mpa(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

    private Entity mapRowToEntity(ResultSet resultSet, int rowNum) throws SQLException {
        return new Entity(resultSet.getInt("id"));
    }
}
