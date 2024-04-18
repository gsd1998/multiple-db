package com.diveintodev.db.controller;

import com.diveintodev.db.admin.entity.Admin;
import com.diveintodev.db.admin.repository.AdminRepo;
import com.diveintodev.db.user.entity.User;
import com.diveintodev.db.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MultiDbController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AdminRepo adminRepo;

    @PostMapping(value = "/add-user")
    public User addUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @PostMapping(value = "/add-admin")
    public Admin addAdmin(@RequestBody Admin admin){
        return adminRepo.save(admin);
    }

}
