package org.example.userservice.service;

import org.example.userservice.model.Role;
import org.example.userservice.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Optional<Role> findByTitle(String title) {
        return roleRepo.findByTitle(title);
    }

    @Transactional
    public void save(Role role) {
        roleRepo.save(role);
    }
}
