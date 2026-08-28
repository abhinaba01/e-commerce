package com.ecommerce.userservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6,message = "Password must be atleast 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

                            // Set automatically on INSERT
//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;
//
//                               // Updated automatically on every UPDATE
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;


    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}