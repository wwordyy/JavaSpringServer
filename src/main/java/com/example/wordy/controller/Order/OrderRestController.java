package com.example.wordy.controller.Order;

import com.example.wordy.model.CustomerModel;
import com.example.wordy.model.OrderModel;
import com.example.wordy.model.OrderStatus;
import com.example.wordy.service.CustomerService;
import com.example.wordy.service.OrderService;
import com.example.wordy.service.OrderStatusService;
import com.example.wordy.util.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderRestController(OrderService orderService, CustomerService customerService, OrderStatusService orderStatusService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderStatusService = orderStatusService;
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<?> createOrder(@PathVariable("id") int id) {

        CustomerModel customer = customerService.findById(id);
        OrderStatus status = orderStatusService.findById(1).orElse(null); // 1 - const можно доделать


        OrderModel orderUser = new OrderModel(customer, status, id); // id для создания номера заказа
        orderService.save(orderUser);

        System.out.println(orderUser);

        return ResponseEntity.ok(returnResponse(status, customer, orderUser));

    }


    private Map<String, Object> returnResponse(OrderStatus status, CustomerModel customer, OrderModel order)
    {
        long sum = Calculate.sumOfAllProducts(customer.getBasket());
        Map<String, Object> response = new HashMap<>();
        response.put("sum", sum);
        response.put("status", status.getTitle());
        response.put("idOrder", order.getId());
        return response;
    }
}
