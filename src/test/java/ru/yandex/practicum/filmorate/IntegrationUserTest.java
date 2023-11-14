package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IntegrationUserTest {

    private final JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void initTest() {

    }

    @Test
    public void validCreateUser() {

        User newUser = new User(1, "user@email.ru", "vanya123", "Ivan Petrov", Date.valueOf("2000-01-01"));
        UserDao userDao = new UserDao(jdbcTemplate);
        userDao.create(newUser);

        User savedUser = userDao.get(1);

        assertThat(savedUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(newUser);
    }

    @Test
    public void validUpdateUser() {

        User newUser = new User(1, "user@email.ru", "vanya123", "Ivan Petrov", Date.valueOf("2000-01-01"));
        UserDao userDao = new UserDao(jdbcTemplate);
        userDao.create(newUser);

        User updatedUser = new User(1, "user_updated@email.ru", "vanya123_updated", "Ivan Petrov upd", Date.valueOf("2000-01-01"));
        userDao.update(updatedUser);

        User savedUser = userDao.get(1);

        assertThat(savedUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(updatedUser);
    }
}
