package ru.anton.library.util;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.anton.library.models.Book;

import java.time.LocalDate;
@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if(((Book) o).getYear() > LocalDate.now().getYear()){
            errors.rejectValue("year", "", "Year should not be greater than present year");
        }
    }
}
