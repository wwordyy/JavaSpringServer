package com.example.wordy.service;


import com.example.wordy.model.OrderModel;
import com.example.wordy.repository.OrderRepo;
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

    public List<OrderModel> findAll() {
        return orderRepo.findAll();
    }

    public Optional<OrderModel> findById(int id) {
        return orderRepo.findById(id);
    }

    public Optional<OrderModel> findByNumberOrder(String numberOrder) {
        return orderRepo.findByNumberOrder(numberOrder);
    }

    @Transactional
    public void save(OrderModel order) {
        orderRepo.save(order);
    }

    @Transactional
    public void delete(OrderModel order) {
        orderRepo.delete(order);
    }

    @Transactional
    public void update(OrderModel order, int idUpd) {
        order.setId(idUpd);
        orderRepo.save(order);
    }
}
