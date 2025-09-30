package org.example.orderservice.service;

import org.example.orderservice.model.Cheque;
import org.example.orderservice.repository.ChequeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChequeService {

    private final ChequeRepo chequeRepo;

    @Autowired
    public ChequeService(ChequeRepo chequeRepo) {
        this.chequeRepo = chequeRepo;
    }

    public List<Cheque> findAll() {
        return chequeRepo.findAll();
    }

    public Optional<Cheque> findById(int id) {
        return chequeRepo.findById(id);
    }

    @Transactional
    public void save(Cheque cheque) {
        chequeRepo.save(cheque);
    }
}
