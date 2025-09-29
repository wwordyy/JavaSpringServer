package com.example.wordy.service;

import com.example.wordy.model.CustomerModel;
import com.example.wordy.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerModel> findAll() {
        return customerRepo.findAll();
    }

    public CustomerModel findById(int id) {
        return customerRepo.findById(id).get();
    }

    @Transactional
    public void save(CustomerModel customer) {
        customerRepo.save(customer);
    }

    @Transactional
    public void delete(CustomerModel customerModel) {
        customerRepo.delete(customerModel);
    }

    @Transactional
    public void update(int idUpd, CustomerModel customer) {
        customer.setIdCustomer(idUpd);
        customerRepo.save(customer);
    }
}
