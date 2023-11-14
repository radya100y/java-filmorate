package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDao;

import java.sql.Date;
@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIntegrationTest extends BaseIntegrationTest<User, UserDao> {

    public UserIntegrationTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }



//    public UserIntegrationTest(JdbcTemplate jdbcTemplate) {
//        super(jdbcTemplate);
////        baseEntity = new User(1, "user@email.ru", "vanya123", "Ivan Petrov", Date.valueOf("2000-01-01"));
////        secondEntity = new User(1, "user_updated@email.ru", "vanya123_updated", "Ivan Petrov upd", Date.valueOf("2000-01-01"));
//        dao = new UserDao(jdbcTemplate);
//    }
}
