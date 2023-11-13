package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IntegrationUserTest {
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void testFindUserById() {
        User newUser = new User(1, "user@email.ru", "vanya123", "Ivan Petrov", Date.valueOf("2000-01-01"));
        UserDao userDao = new UserDao(jdbcTemplate);
        userDao.create(newUser);

        User savedUser = userDao.get(1);

        assertThat(savedUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(newUser);
    }
}
