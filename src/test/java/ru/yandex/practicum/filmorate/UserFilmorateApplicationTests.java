package ru.yandex.practicum.filmorate;

import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;
import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserFilmorateApplicationTests extends BaseFilmorateApplicationTest<UserController, User> {
    public UserFilmorateApplicationTests() {
        uri = URI.create("http://localhost:8080/users");

        validBody = "{\"login\": \"dolore\",\"name\": \"Nick Name\",\"email\": \"mail@mail.ru\"," +
                "\"birthday\": \"1946-08-20\"}";

        invalidBody1 = "{\"login\": \"dolore\",\"name\": \"Nick Name\",\"email\": \"mail.ru\"," +
                "\"birthday\": \"1946-08-20\"}";

        invalidBody2 = "{\"login\": \"\",\"name\": \"Nick Name\",\"email\": \"mail@mail.ru\"," +
                "\"birthday\": \"1946-08-20\"}";

        invalidBody3 = "{\"login\": \"dolore\",\"name\": \"Nick Name\",\"email\": \"mail@mail.ru\"," +
                "\"birthday\": \"2024-01-01\"}";

        validUpdateBody = "{\"id\": 1,\"login\": \"dolore updated\",\"name\": \"Nick Updated\"," +
                "\"email\": \"mail@mail.ru\",\"birthday\": \"1946-08-20\"}";

        invalidUpdateBody = "{\"id\": 999,\"login\": \"dolore\",\"name\": \"Nick Name\",\"email\": \"mail@mail.ru\"," +
                "\"birthday\": \"1946-08-20\"}";
    }

    void contextLoads() {
    }
}
