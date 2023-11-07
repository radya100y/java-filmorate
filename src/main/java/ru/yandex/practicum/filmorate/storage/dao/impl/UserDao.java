package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.error.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@Primary
public class UserDao implements BaseStorage<User> {

    private final JdbcTemplate jdbcTemplate;
    private String sqlQuery;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void validate(User user) {
        if (user.getName().isBlank()) user.setName(user.getLogin());
    }

    @Override
    public User create(User fact) {
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
        return get(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    @Override
    public User update(User fact) { //EmptyResultDataAccessException
        sqlQuery = "update _user set email = ?, login = ?, name = ?, birthday = ? where id = ?";
        jdbcTemplate.update(sqlQuery
                , fact.getEmail()
                , fact.getLogin()
                , fact.getName()
                , fact.getBirthday()
                , fact.getId());
        return get(fact.getId());
    }

    @Override
    public List<User> getAll() {
        sqlQuery = "select id, email, login, name, birthday from _user";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser);
    }

    @Override
    public User get(Integer userId) {
        sqlQuery = "select id, email, login, name, birthday from _user where id = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToUser, userId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Пользователь " + userId + " не найден");
        }
    }

    public List<User> getFriends(Integer userId) {
        get(userId);
        sqlQuery = "select id, email, login, name, birthday from _user where id in (select related_user_id " +
                "from user_friend where user_id = ?)";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, userId);
    }

    public List<User> getCommonFriends(Integer userId, Integer relatedUserId) {
        get(userId);
        get(relatedUserId);
        sqlQuery = "select id, email, login, name, birthday from _user where id in (select related_user_id " +
                "from user_friend where user_id in (?, ?) group by related_user_id having count(user_id) = 2)";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, userId, relatedUserId);
    }

    public List<User> addFriend(Integer userId, Integer relatedUserId) {
        get(userId);
        get(relatedUserId);
        sqlQuery = "insert into user_friend (user_id, related_user_id) values (?, ?) ";
        jdbcTemplate.update(sqlQuery, userId, relatedUserId);
        return getFriends(userId);
    }

    public List<User> delFriend(Integer userId, Integer relatedUserId) {
        get(userId);
        get(relatedUserId);
        sqlQuery = "delete user_friend where user_id = ? and related_user_id = ?";
        jdbcTemplate.update(sqlQuery, userId, relatedUserId);
        return getFriends(userId);
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException, EmptyResultDataAccessException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("login"),
                resultSet.getString("name"),
                resultSet.getDate("birthday")
        );
    }
}
