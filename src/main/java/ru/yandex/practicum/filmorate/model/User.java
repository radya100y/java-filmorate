package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class User extends Entity {

    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String login;

    @Value("${login}")
    private String name;

    @PastOrPresent
    private LocalDate birthday;
}
