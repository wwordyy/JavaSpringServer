package org.example.userservice.repository;

import org.example.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByTitle(String title);
}
