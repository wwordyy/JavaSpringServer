package com.example.wordy.controller.Order;

import com.example.wordy.model.OrderModel;
import com.example.wordy.service.*;
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
public class OrderController {

    private final OrderService orderService;

    private final OrderStatusService orderStatusService;

    private final CustomerService customerService;

    private final BasketService basketService;

    private final CheckService checkService;

    @Autowired
    public OrderController(OrderService orderService, OrderStatusService orderStatusService, CustomerService customerService, BasketService basketService, CheckService checkService) {
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.customerService = customerService;
        this.basketService = basketService;
        this.checkService = checkService;
    }


    private void getAll(Model model) {
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("orderStatuses", orderStatusService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("baskets", basketService.findAll());
        model.addAttribute("checks", checkService.findAll());
    }


    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        getAll(model);

        model.addAttribute("order", new OrderModel());
        return "Order/orders";
    }

    @PostMapping("/orders/add")
    public String addOrder(@ModelAttribute("order") @Valid OrderModel orderModel,
                           BindingResult bindingResult, Model model) {
        try {


            if (bindingResult.hasErrors()) {
                getAll(model);
                return "Order/orders";
            }
            orderService.save(orderModel);
            return "redirect:/orders";
        }
        catch (Exception e) {
            getAll(model);
            return "redirect:/orders";
        }
    }

    @PostMapping("/orders/update")
    public String updateOrder(@RequestParam int id,
                              @ModelAttribute("order") @Valid OrderModel orderModel,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getAll(model);
            return "Order/orders";
        }

        OrderModel existingOrder = orderService.findById(id).orElseThrow();
        existingOrder.setNumberOrder(orderModel.getNumberOrder());
        existingOrder.setCustomer(orderModel.getCustomer());
        existingOrder.setOrderStatusId(orderModel.getOrderStatusId());
        orderService.save(existingOrder);

        return "redirect:/orders";
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@RequestParam int id) {
        OrderModel orderModel = orderService.findById(id).orElseThrow();
        orderService.delete(orderModel);
        return "redirect:/orders";
    }

    @PostMapping("/orders/findById")
    public String findOrderById(@RequestParam int id, Model model) {
        Optional<OrderModel> orderData = orderService.findById(id);
        if (orderData.isPresent()) {
            model.addAttribute("order", orderData.get());
            return "Order/orderDetails";
        } else {
            return "redirect:/orders";
        }
    }

    @PostMapping("/orders/findByNumberOrder")
    public String findOrderByNumber(@RequestParam String numberOrder, Model model) {
        Optional<OrderModel> orderModel = orderService.findByNumberOrder(numberOrder);
        if (orderModel.isPresent()) {
            model.addAttribute("order", orderModel.get());
            return "Order/orderDetails";
        } else {
            return "redirect:/orders";
        }
    }
}

