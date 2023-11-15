package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@Primary
public class FilmDao implements BaseStorage<Film> {

    private final JdbcTemplate jdbcTemplate;
    private String sqlQuery;
    private final MpaDao mpaDao;
    private final GenreDao genreDao;

    public FilmDao(JdbcTemplate jdbcTemplate, MpaDao mpaDao, GenreDao genreDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.mpaDao = mpaDao;
        this.genreDao = genreDao;
    }

    @Override
    public void validate(Film fact) {

    }

    @Override
    public Film create(Film fact) {
        sqlQuery = "insert into film (name, description, release_date, duration, rate, mpa) " +
                "values (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(x -> {
                    PreparedStatement stmt = x.prepareStatement(sqlQuery, new String[]{"id"});
                    stmt.setString(1, fact.getName());
                    stmt.setString(2, fact.getDescription());
                    stmt.setObject(3, fact.getReleaseDate());
                    stmt.setInt(4, fact.getDuration());
                    stmt.setInt(5, fact.getRate());
                    stmt.setInt(6, fact.getMpa().getId());
                    return stmt;
                }, keyHolder
        );
        return get(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    @Override
    public Film update(Film fact) {
        sqlQuery = "update film set name = ?, description = ?, release_date = ?, " +
                "duration = ?, rate = ?, mpa = ? where id = ?";
        jdbcTemplate.update(sqlQuery,
                fact.getName(),
                fact.getDescription(),
                fact.getReleaseDate(),
                fact.getDuration(),
                fact.getRate(),
                fact.getMpa().getId(),
                fact.getId()
        );
        return get(fact.getId());
    }

    @Override
    public List<Film> getAll() {
        sqlQuery = "select id, name, description, release_date, duration, rate, mpa from film";
        List<Film> filmList = jdbcTemplate.query(sqlQuery, new FilmMapRow());
        return filmList;
    }

    public List<Film> getPopular(Integer count) {
        sqlQuery = "select id, name, description, release_date, duration, rate, mpa from film as f " +
                "left join (select film_id, count(user_id) as qty from user_like_film group by film_id) as r " +
                "on r.film_id = f.id order by r.qty desc limit ?";
        return jdbcTemplate.query(sqlQuery, new FilmMapRow(), count);
    }

    @Override
    public Film get(Integer filmId) {
        sqlQuery = "select id, name, description, release_date, duration, rate, mpa from film where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new FilmMapRow(), filmId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Фильм " + filmId + " не найден");
        }
    }


    public Film addLike(Integer filmId, Integer userId) {
        sqlQuery = "merge into user_like_film key(user_id, film_id) values (?, ?)";
        jdbcTemplate.update(sqlQuery, userId, filmId);
        return get(filmId);
    }

    public Film delLike(Integer filmId, Integer userId) {
        sqlQuery = "delete user_like_film where user_id = ? and film_id = ?";
        jdbcTemplate.update(sqlQuery, userId, filmId);
        return get(filmId);
    }

    private class FilmMapRow implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getInt("id");
            return new Film(
                    id,
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("release_date"),
                    rs.getInt("duration"),
                    rs.getInt("rate"),
                    mpaDao.get(rs.getInt("mpa")),
                    genreDao.getFilmGenres(rs.getInt("id"))
            );
        }
    }
}
