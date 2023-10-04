package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FilmorateApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void validateFilmFail() {
		Film film = new Film();
		film.setName("qwe");
		film.setDuration(10);
		film.setReleaseDate(LocalDate.of(2000, 01, 1));
		FilmController films = new FilmController();
		films.create(film);
		System.out.println(films.get());
//		assertThrows(ValidateException.class, () -> films.create(film));
	}

}
