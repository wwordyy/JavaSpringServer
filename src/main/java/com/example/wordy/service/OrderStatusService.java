package com.example.wordy.service;

import com.example.wordy.model.OrderStatus;
import com.example.wordy.repository.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class OrderStatusService{

    private final OrderStatusRepo orderStatusRepo;


    @Autowired
    public OrderStatusService(OrderStatusRepo orderStatusRepo) {
        this.orderStatusRepo = orderStatusRepo;
    }

    public List<OrderStatus> findAll() {
        return orderStatusRepo.findAll();
    }

    public Optional<OrderStatus> findById(int id) {
        return orderStatusRepo.findById(id);
    }

    public Optional<OrderStatus> findByTitle(String title) {
        return orderStatusRepo.findByTitle(title);
    }

    @Transactional
    public void save(OrderStatus orderStatus) {
        orderStatusRepo.save(orderStatus);
    }

    @Transactional
    public void delete(OrderStatus orderStatus) {
        orderStatusRepo.delete(orderStatus);
    }

    @Transactional
    public void update(OrderStatus orderStatus, int idUpd) {
        orderStatus.setId(idUpd);
        orderStatusRepo.save(orderStatus);
    }
}
