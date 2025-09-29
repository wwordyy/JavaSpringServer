package com.example.wordy.controller.AuthController;

import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.service.PersonalDateService;
import com.example.wordy.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    private final PersonValidator personValidator;
    private final PersonalDateService personalDateService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public RegistrationRestController(PersonValidator personValidator,
                                      PersonalDateService personalDateService,
                                      PasswordEncoder passwordEncoder)
    {
        this.personValidator = personValidator;
        this.personalDateService = personalDateService;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid PersonalDateModel personalDateModel,
                                                          BindingResult bindingResult) {
        personValidator.validate(personalDateModel, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        personalDateModel.setPassword(passwordEncoder.encode(personalDateModel.getPassword()));
        personalDateModel.setRole("ROLE_USER");


        personalDateService.save(personalDateModel);
        personalDateService.createCustomer(personalDateModel);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
