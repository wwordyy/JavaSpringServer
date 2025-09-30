package org.example.orderservice.service;

import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findAll()
    {
        return orderRepo.findAll();
    }

    public Optional<Order> findById(int id)
    {
        return orderRepo.findById(id);
    }

    @Transactional
    public void save(Order order)
    {
        orderRepo.save(order);
    }
}
