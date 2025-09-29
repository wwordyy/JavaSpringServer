package com.example.wordy.controller.AuthController;


import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.service.PersonalDateService;
import com.example.wordy.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    private final PersonValidator personValidator;
    private final PersonalDateService personalDateService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public RegistrationController(PersonValidator personValidator, PersonalDateService personalDateService, PasswordEncoder passwordEncoder) {
        this.personValidator = personValidator;
        this.personalDateService = personalDateService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/auth/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("personalDate", new PersonalDateModel());
        return "Auth/registration";
    }

//    @PostMapping("/auth/registration")
//    public String postRegistration(@ModelAttribute("personalDate") @Valid PersonalDateModel personalDateModel,
//                                   BindingResult bindingResult) {
//        personValidator.validate(personalDateModel, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "Auth/registration";
//        }
//        personalDateModel.setPassword(passwordEncoder.encode(personalDateModel.getPassword()));
//        personalDateModel.setRole("ROLE_USER");
//
//        personalDateService.save(personalDateModel);
//
//        return "redirect:/auth/login";
//
//    }
}



