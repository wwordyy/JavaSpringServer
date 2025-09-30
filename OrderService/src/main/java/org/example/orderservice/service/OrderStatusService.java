package org.example.orderservice.service;

import org.example.orderservice.model.OrderStatus;
import org.example.orderservice.repository.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderStatusService {

    private final OrderStatusRepo orderStatusRepo;

    @Autowired
    public OrderStatusService(OrderStatusRepo orderStatusRepo) {
        this.orderStatusRepo = orderStatusRepo;
    }

    public List<OrderStatus> findAll()
    {
        return orderStatusRepo.findAll();
    }

    public Optional<OrderStatus> findById(Integer id)
    {
        return orderStatusRepo.findById(id);
    }

    @Transactional
    public void save(OrderStatus orderStatus)
    {
        orderStatusRepo.save(orderStatus);
    }
}
