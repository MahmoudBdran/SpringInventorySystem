package com.bdran.InventorySystem.service;

import com.bdran.InventorySystem.model.Role;
import com.bdran.InventorySystem.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    private final RoleRepo roleRepo;


    public List<Role> findAll(){
        return roleRepo.findAll();
    }
    public Role findById(Long id){
        return roleRepo.findById(id).get();
    }
    public Role findByName(String name){
        return roleRepo.findByName(name);
    }
    public Role insert(Role role){
        return roleRepo.save(role);
    }

}
