package com.ecommerce.userservice.controller;


import com.ecommerce.userservice.dto.LoginRequestDTO;
import com.ecommerce.userservice.dto.LoginResponseDTO;
import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.service.AuthService;
import com.ecommerce.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping("/register")
    public UserResponseDTO register (@Valid @RequestBody UserRequestDTO request){

        return authService.registerUser(request);
    }


    @PostMapping("/login")
    public LoginResponseDTO login (@Valid @RequestBody LoginRequestDTO request){

        return authService.loginUser(request);
    }

}
