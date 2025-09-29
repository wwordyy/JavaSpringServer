package com.example.wordy.controller.OrderStatus;

import com.example.wordy.model.OrderStatus;
import com.example.wordy.service.OrderStatusService;
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
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }


    @GetMapping("/orderStatuses")
    public String getAllOrderStatuses(Model model) {
        Iterable<OrderStatus> orderStatuses = orderStatusService.findAll();
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("orderStatus", new OrderStatus());

        return "OrderStatus/orderStatuses";
    }

    @PostMapping("/orderStatuses/add")
    public String addOrderStatus(@ModelAttribute("orderStatus")
                                 @Valid OrderStatus orderStatus,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OrderStatus/orderStatuses";
        }

        orderStatusService.save(orderStatus);
        return "redirect:/orderStatuses";
    }

    @PostMapping("/orderStatuses/update")
    public String updateOrderStatus(@RequestParam int id,
                                    @ModelAttribute("orderStatus")
                                    @Valid OrderStatus orderStatus,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "OrderStatus/orderStatuses";
        }
        OrderStatus existingOrderStatus = orderStatusService.findById(id).orElseThrow();
        existingOrderStatus.setTitle(orderStatus.getTitle());
        orderStatusService.save(existingOrderStatus);
        return "redirect:/orderStatuses";
    }

    @PostMapping("/orderStatuses/delete")
    public String deleteOrderStatus(@RequestParam int id) {
        OrderStatus orderStatus = orderStatusService.findById(id).orElseThrow();
        orderStatusService.delete(orderStatus);
        return "redirect:/orderStatuses";
    }

    @PostMapping("/orderStatuses/findById")
    public String findOrderStatusById(@RequestParam int id, Model model) {
        try {
            var orderStatusData = orderStatusService.findById(id).orElseThrow();
            model.addAttribute("orderStatus", orderStatusData);
            return "OrderStatus/orderStatusDetails";
        } catch (Exception e) {
            return "redirect:/orderStatuses";
        }
    }

    @PostMapping("/orderStatuses/findByTitle")
    public String findOrderStatusByTitle(@RequestParam String title, Model model) {
        Optional<OrderStatus> orderStatus = orderStatusService.findByTitle(title);
        if (orderStatus.isPresent()) {
            model.addAttribute("orderStatus", orderStatus.get());
            return "OrderStatus/orderStatusDetails";
        } else {
            return "redirect:/orderStatuses";
        }
    }
}
