package ru.test.rest.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class ClientValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;
        if (client.getBirthday().isAfter(LocalDate.of(2005, 12, 31))) {
            errors.rejectValue("birthday", "Client.birthday.invalid");
        }
    }
}
