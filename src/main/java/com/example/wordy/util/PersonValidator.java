package com.example.wordy.util;

import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.service.PersonalDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonalDateService personalDateService;

    @Autowired
    public PersonValidator(PersonalDateService personalDateService) {
        this.personalDateService = personalDateService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return PersonalDateModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonalDateModel personalDateModel = (PersonalDateModel)target;

        Optional<PersonalDateModel> person = personalDateService.findByLogin(personalDateModel.getLogin());

        if (person.isPresent()) {
            errors.rejectValue("login", "","Человек с таким логином уже существует");
        }
    }
}
