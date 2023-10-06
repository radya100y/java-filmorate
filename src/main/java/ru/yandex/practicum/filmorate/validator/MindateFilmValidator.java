package ru.yandex.practicum.filmorate.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MindateFilmValidator implements ConstraintValidator<MinDateFilm, LocalDate> {
    private LocalDate minimumDate;

    @Override
    public void initialize(MinDateFilm constraintAnnotation) {
        minimumDate = LocalDate.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value == null || value.isAfter(minimumDate);
    }
}
