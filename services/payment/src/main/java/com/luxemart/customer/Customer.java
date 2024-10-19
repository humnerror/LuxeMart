package com.luxemart.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        @NotNull(message = "Customer Id is required")
        String id,

        @NotNull(message = "Customer Firstname is required")
        String firstName,

        @NotNull(message = "Customer Lastname is required")
        String lastName,

        @NotNull(message = "Customer Email is required")
        @Email(message = "Invalid Email")
        String email
) {
}
