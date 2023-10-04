package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.error.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class FilmorateApplicationTests extends BaseFilmorateApplicationTest<FilmController, Film> {
	@BeforeEach
	void contextLoads() {
		controller = new FilmController();
		entity = new Film();
	}
	@Test
	void validAddNew() {
		entity.setName("first");
		entity.setReleaseDate(LocalDate.of(1895, 12, 29));
		entity.setDuration(100);
		controller.create(entity);
		assertEquals(controller.get().size(), 1);
	}
	@Test
	void failAddBlankName() {
		entity.setName("");
		entity.setReleaseDate(LocalDate.of(2000, 1, 1));
		entity.setDuration(100);
		assertThrows(ValidateException.class, () -> controller.create(entity));
	}
	@Test
	void failAddZeroDuration() {
		entity.setName("first");
		entity.setReleaseDate(LocalDate.of(2000, 1, 1));
		entity.setDuration(0);
		assertThrows(ValidateException.class, () -> controller.create(entity));
	}
	@Test
	void failAddIllegalDate() {
		entity.setName("first");
		entity.setReleaseDate(LocalDate.of(1895, 12, 27));
		entity.setDuration(100);
		assertThrows(ValidateException.class, () -> controller.create(entity));
	}
}
