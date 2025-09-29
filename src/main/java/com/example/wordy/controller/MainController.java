package com.example.wordy.controller;

import com.example.wordy.security.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final PeopleDetailsService personDetails;

    @Autowired
    public MainController(PeopleDetailsService personDetails) {
        this.personDetails = personDetails;
    }
    @GetMapping("/home")
    public String getMainPage(Model model)
    {
        model.addAttribute("role", personDetails.checkCurrentUser());
        return "/Main/home";
    }

    @GetMapping("/admin")
    public String getAdminPage()
    {
        return "/Main/admin";

    }


}
