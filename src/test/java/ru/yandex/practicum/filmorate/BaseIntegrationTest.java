package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.Entity;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class BaseIntegrationTest<T extends Entity, E extends BaseStorage<T>> {
    protected T baseEntity;
//    protected T secondEntity;
    protected E dao;
    protected final JdbcTemplate jdbcTemplate;

    @Test
    public void create() {
        dao.create(baseEntity);
        T savedEntity = dao.get(1);

        assertThat(savedEntity)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedEntity);
    }
}
