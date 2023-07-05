package ru.anton.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.anton.library.models.Person;

import java.time.LocalDate;
@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(person.getYear_of_birth() > LocalDate.now().getYear())
            errors.rejectValue("year_of_birth", "", "Year should not be greater than present year");
    }
}
