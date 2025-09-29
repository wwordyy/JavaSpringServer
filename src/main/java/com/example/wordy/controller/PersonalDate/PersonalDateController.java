package com.example.wordy.controller.PersonalDate;

import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.service.PersonalDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PersonalDateController {

    private final PersonalDateService personalDateService;

    @Autowired
    public PersonalDateController(PersonalDateService personalDateService) {
        this.personalDateService = personalDateService;
    }


    @GetMapping("/personalDate")
    public String getPersonalDates(Model model) {
        model.addAttribute("personalDates", personalDateService.findAll());
        model.addAttribute("personalDate", new PersonalDateModel());

        return "PersonalDate/personalDate";
    }

    @PostMapping("/personalDate/add")
    public String addPersonalDate(@ModelAttribute("personalDate") @Valid PersonalDateModel personalDate,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "PersonalDate/personalDate";
        }

        personalDateService.save(personalDate);
        return "redirect:/personalDate";
    }


    @PostMapping("/personalDate/update")
    public String updatePersonalDate(@RequestParam int id,
                                     @ModelAttribute("personalDate")
                                     @Valid PersonalDateModel personalDate,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/personalDate";
        }
        PersonalDateModel personalDateModel = personalDateService.findById(id).orElseThrow();
        personalDateModel.setLogin(personalDate.getLogin());
        personalDateModel.setPassword(personalDate.getPassword());
        personalDateService.save(personalDateModel);
        return "redirect:/personalDate";
    }

    @PostMapping ("/personalDate/delete")
    public String deletePersonalDate(@RequestParam int id) {
        PersonalDateModel personalDateModel = personalDateService.findById(id).orElseThrow();
        personalDateService.delete(personalDateModel);
       return "redirect:/personalDate";
    }

    @PostMapping ("/personalDate/findById")
    public String findPersonalDateById(@RequestParam int id,
                                       Model model) {

        try {
            var personalDate = personalDateService.findById(id).orElseThrow();
            if (personalDate != null) {
                model.addAttribute("personalDate", personalDate);
                return "PersonalDate/personalDateDetails";
            } else {
                return "redirect:/personalDate";
            }
        }
        catch (Exception e) {
            return "redirect:/personalDate";
        }
    }


    @PostMapping("/personalDate/findByLogin")
    public String findPersonalDateByLogin(@RequestParam String login,
                                          Model model) {
        Optional<PersonalDateModel> personalDate = personalDateService.findByLogin(login);
        if (personalDate.isPresent()) {
            model.addAttribute("personalDate", personalDate.get());
            return "PersonalDate/personalDateDetails";
        } else {
            return "redirect:/personalDate";
        }

    }


}
