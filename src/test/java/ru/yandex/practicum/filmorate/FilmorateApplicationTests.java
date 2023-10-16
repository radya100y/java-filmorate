package ru.yandex.practicum.filmorate;

import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FilmorateApplicationTests extends BaseFilmorateApplicationTest<FilmController, Film> {

	public FilmorateApplicationTests() {
		uri = URI.create("http://localhost:8080/films");

		validBody = "{\"name\": \"nisi eiusmodq\",\"description\": \"adipisicing\"," +
				"\"releaseDate\": \"1895-12-29\",\"duration\": 100}";

		invalidBody1 = "{\"name\": \"\",\"description\": \"adipisicing\"," +
				"\"releaseDate\": \"1967-03-25\",\"duration\": 100}";

		invalidBody2 = "{\"name\": \"nisi eiusmodq\",\"description\": " + "1".repeat(201) + "," +
				"\"releaseDate\": \"1967-03-25\",\"duration\": 100}";

		invalidBody3 = "{\"name\": \"nisi eiusmodq\",\"description\": \"adipisicing\"," +
				"\"releaseDate\": \"1895-12-27\",\"duration\": 100}";

		validUpdateBody = "{\"id\": 1,\"name\": \"Film Updated\",\"releaseDate\": \"1989-04-17\"," +
				"\"description\": \"New film update decription\",\"duration\": 190,\"rate\": 4}";
		invalidUpdateBody = "{\"id\": 999,\"name_updated\": \"nisi updated\",\"description\": \"qwe\"," +
				"\"releaseDate\": \"1895-12-29\",\"duration\": 1000}";
	}
}
