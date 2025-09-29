package com.example.wordy.controller.Customer;

import com.example.wordy.model.CustomerModel;
import com.example.wordy.service.CustomerService;
import com.example.wordy.service.PersonalDateService;
import com.example.wordy.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CustomerController {

    public final CustomerService customerService;

    public final PersonalDateService personalDateService;

    public final ShopService shopsService;

    @Autowired
    public CustomerController(CustomerService customerService, PersonalDateService personalDateService, ShopService shopsService) {
        this.customerService = customerService;
        this.personalDateService = personalDateService;
        this.shopsService = shopsService;
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {

        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("personalDates", personalDateService.findAll());
        model.addAttribute("shops", shopsService.findAll());

        model.addAttribute("customer", new CustomerModel());

        return "Customer/customers";

    }


    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customer") @Valid CustomerModel customersModel,
                              BindingResult bindingResult, Model model) {

        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("personalDates", personalDateService.findAll());
                model.addAttribute("shops", shopsService.findAll());
                return "Customer/customers";
            }

            customerService.save(customersModel);

            return "redirect:/customers";
        }
        catch (Exception e) {
            model.addAttribute("personalDates", personalDateService.findAll());
            model.addAttribute("shops", shopsService.findAll());
            return "redirect:/customers";
        }


    }

    @PostMapping("/customers/update")
    public String updateCustomer(@RequestParam int id,
                                 @ModelAttribute("customer") @Valid CustomerModel customersModel,
                                 BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "Customer/customers";
        }

        CustomerModel customersModel1 = customerService.findById(id);
        customersModel1.setShops(customersModel.getShops());
        customersModel1.setPersonalDate(customersModel.getPersonalDate());
        customerService.save(customersModel1);

        return "redirect:/customers";
    }


    @PostMapping("/customers/delete")
    public String deleteCustomer(@RequestParam int id) {

        CustomerModel customersModel = customerService.findById(id);
        customerService.delete(customersModel);
        return "redirect:/customers";
    }

    @PostMapping("/customers/findById")
    public String findCustomerById(@RequestParam int id, Model model) {

        try {
            var customer = customerService.findById(id);

            if (customer != null) {
                model.addAttribute("customer", customer);
                return "Customer/customerDetails";
            } else {
                return "redirect:/customers";
            }
        }
        catch (Exception e) {
            return "redirect:/customers";
        }
    }
}