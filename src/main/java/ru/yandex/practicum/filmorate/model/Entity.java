package ru.yandex.practicum.filmorate.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Entity {

    public int id;

    public Entity() {}
    public Entity(int id) {
        this.id = id;
    }
}
