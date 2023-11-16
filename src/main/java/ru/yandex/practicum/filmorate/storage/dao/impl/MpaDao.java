package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
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
        return jdbcTemplate.query(sqlQuery, new MpaMapRow());
    }

    @Override
    public Mpa get(Integer id) {
        sqlQuery = "select id, name from mpa where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new MpaMapRow(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Рейтинг " + id + " не найден");
        }
    }

    private static class MpaMapRow implements RowMapper<Mpa> {

        @Override
        public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getInt("id");
            return new Mpa(
                    id,
                    rs.getString("name")
            );
        }
    }
}
