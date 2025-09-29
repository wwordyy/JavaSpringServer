package com.example.wordy.controller.OrderStatus;

import com.example.wordy.model.OrderStatus;
import com.example.wordy.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderStatuses")
@Tag(name = "OrderStatuses", description = "API для управления статусами заказов")
public class OrderStatusRestController {

    public final OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusRestController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех статусов заказов")
    public List<OrderStatus> getOrderStatus() {
        return orderStatusService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод статуса по его ID")
    public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable int id) {
        Optional<OrderStatus> orderStatus = orderStatusService.findById(id);

        if (orderStatus.isPresent()) {
            return new ResponseEntity<>(orderStatus.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление статуса по его ID")
    public ResponseEntity<HttpStatus> updateOrderStatus(@PathVariable int id,
                                                        @RequestBody @Valid OrderStatus orderStatus,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderStatus newOrderStatus = orderStatusService.findById(id).orElse(null);

        if (newOrderStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newOrderStatus.setTitle(orderStatus.getTitle());
        newOrderStatus.setOrders(orderStatus.getOrders());
        orderStatusService.save(newOrderStatus);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Создание статуса заказа")
    public ResponseEntity<HttpStatus> createOrderStatus(@RequestBody @Valid OrderStatus orderStatus,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        orderStatusService.save(orderStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление статуса по ID")
    public ResponseEntity<HttpStatus> deleteOrderStatus(@PathVariable int id) {
        Optional<OrderStatus> orderStatus = orderStatusService.findById(id);
        if (orderStatus.isPresent()) {
            orderStatusService.delete(orderStatus.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
