package ru.yandex.practicum.filmorate.error;

public class NotFoundException extends RuntimeException {
    Integer entityId;

    public NotFoundException(Integer entityId) {
        super();
        this.entityId = entityId;
    }
}
