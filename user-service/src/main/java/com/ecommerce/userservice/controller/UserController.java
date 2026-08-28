package com.ecommerce.userservice.controller;


import com.ecommerce.userservice.dto.LoginRequestDTO;
import com.ecommerce.userservice.dto.LoginResponseDTO;
import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        
        return userService.findUserById(id);

    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){

        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){

        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@Valid @PathVariable Long id , @RequestBody UserRequestDTO request){


        return userService.updateUser(id,request);
    }

}