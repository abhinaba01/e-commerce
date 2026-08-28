package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.entity.Role;

public class LoginResponseDTO {

    private Long id;
    private String message;
    private String email;
    private Role role;
    private String token;

    
    public LoginResponseDTO() {
    }

    public LoginResponseDTO(
            Long id,
            String message,
            String email,
            Role role,
            String token
    ) {
        this.id = id;
        this.message = message;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String gettoken() {
        return token;
    }

    public void settoken(String token) {
        this.token = token;
    }
}
