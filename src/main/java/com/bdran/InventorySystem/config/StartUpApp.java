package com.bdran.InventorySystem.config;


import com.bdran.InventorySystem.model.AppUser;
import com.bdran.InventorySystem.model.Role;
import com.bdran.InventorySystem.service.RoleService;
import com.bdran.InventorySystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
    if(roleService.findAll().isEmpty()){
        roleService.insert(new Role(null,"admin"));
        roleService.insert(new Role(null,"user"));
        roleService.insert(new Role(null,"employee"));
    }

        if(userService.findAll().isEmpty()){
            Set<Role> adminRoles= new HashSet<>();
            adminRoles.add(roleService.findByName("admin"));
            Set<Role> userRoles= new HashSet<>();
            userRoles.add(roleService.findByName("user"));
            Set<Role> employeeRoles= new HashSet<>();
            employeeRoles.add(roleService.findByName("employee"));
            userService.insert(new AppUser(null,"mahmoud bdran","mahmoud","123",adminRoles));
            userService.insert(new AppUser(null,"ahmed hassan","ahmed","123",userRoles));
            userService.insert(new AppUser(null,"hassan hassona","hassan","123",employeeRoles));
        }


    }
}
