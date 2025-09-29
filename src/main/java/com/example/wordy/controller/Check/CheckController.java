package com.example.wordy.controller.Check;

import com.example.wordy.model.CheckModel;
import com.example.wordy.service.CheckService;
import com.example.wordy.service.OrderService;
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
public class CheckController {

    private final CheckService checkService;

    private final OrderService orderService;

    @Autowired
    public CheckController(CheckService checkService, OrderService orderService) {
        this.checkService = checkService;
        this.orderService = orderService;
    }


    private void getAll(Model model)
    {
        model.addAttribute("checks", checkService.findAll());
        model.addAttribute("orders", orderService.findAll());
    }

    @GetMapping("/checks")
    public String getAllChecks(Model model) {

        getAll(model);
        model.addAttribute("check", new CheckModel());
        return "Check/checks";
    }


    @PostMapping("/checks/add")
    public String addCheck(@ModelAttribute("check") @Valid CheckModel check,
                           BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                getAll(model);
                return "Check/checks";
            }


            checkService.save(check);

            return "redirect:/checks";
        }
        catch (Exception e) {
            return "redirect:/checks";
        }
    }

    @PostMapping("/checks/update")
    public String updateCheck(@RequestParam int id,
                              @ModelAttribute("check") @Valid CheckModel checkModel,
                              BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                getAll(model);
                return "Check/checks";
            }

            CheckModel newCheck = checkService.findById(id).orElseThrow();
            newCheck.setCheckIssueDate(checkModel.getCheckIssueDate());
            newCheck.setOrder(checkModel.getOrder());
            newCheck.setTotalPrice(checkModel.getTotalPrice());
            checkService.save(newCheck);


            return "redirect:/checks";
        }
        catch (Exception e) {
            getAll(model);
            return "Check/checks";
        }
    }

    @PostMapping("/checks/delete")
    public String deleteCheck(@RequestParam int id) {
        CheckModel deleteCheck = checkService.findById(id).orElseThrow();
        checkService.delete(deleteCheck);
        return "redirect:/checks";
    }

    @PostMapping("/checks/findById")
    public String findCheckById(@RequestParam int id, Model model) {
        CheckModel foundCheck = checkService.findById(id).orElseThrow();
        if (foundCheck != null) {
            model.addAttribute("check", foundCheck);
            return "Check/checkDetails";
        } else {
            return "redirect:/checks";
        }
    }


    @PostMapping("/checks/findByTotalPrice")
    public String findByTotalPrice(@RequestParam int totalPrice,
                                          Model model) {
        Optional<CheckModel> checkModel = checkService.findByTotalPrice(totalPrice);
        if (checkModel.isPresent()) {
            model.addAttribute("check", checkModel.get());
            return "Check/checkDetails";
        } else {
            return "redirect:/checks";
        }

    }


}
