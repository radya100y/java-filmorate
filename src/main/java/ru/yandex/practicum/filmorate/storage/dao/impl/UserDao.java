package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.dao.BaseStorageDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserDao implements BaseStorageDao<User> {

    private final JdbcTemplate jdbcTemplate;
    private String sqlQuery;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validate(User user) {
        if (user.getName().isBlank()) user.setName(user.getLogin());
    }

    @Override
    public User create(User fact) throws SQLException {
        validate(fact);
        sqlQuery = "insert into _user (email, login, name, birthday) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(x -> {
                    PreparedStatement stmt = x.prepareStatement(sqlQuery, new String[]{"id"});
                    stmt.setString(1, fact.getEmail());
                    stmt.setString(2, fact.getLogin());
                    stmt.setString(3, fact.getName());
                    stmt.setObject(4, fact.getBirthday());
                    return stmt;
                }, keyHolder
        );
        return get(Objects.requireNonNull(keyHolder.getKey()).intValue()).get();
    }

    @Override
    public User update(User fact) {
        sqlQuery = "update _user set email = ?, login = ?, name = ?, birthday = ? where id = ?";
        jdbcTemplate.update(sqlQuery
                , fact.getEmail()
                , fact.getLogin()
                , fact.getName()
                , fact.getBirthday()
                , fact.getId());
        return get(fact.getId()).get();
    }

    @Override
    public List<User> getAll() {
        sqlQuery = "select id, email, login, name, birthday from _user";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser);
    }

    @Override
    public Optional<User> get(Integer id) {
        sqlQuery = "select id, email, login, name, birthday from _user where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, this::mapRowToUser, id));
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("login"),
                resultSet.getString("name"),
                resultSet.getDate("birthday")
        );
    }
}
