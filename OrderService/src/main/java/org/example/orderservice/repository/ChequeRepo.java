package org.example.orderservice.repository;

import org.example.orderservice.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Integer> {
}
