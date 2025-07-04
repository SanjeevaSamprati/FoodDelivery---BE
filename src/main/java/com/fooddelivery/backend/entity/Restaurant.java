package com.fooddelivery.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(nullable = false, unique = true)
    private String contactNo;

    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    private Float rating;

    // Getters and Setters...
}
