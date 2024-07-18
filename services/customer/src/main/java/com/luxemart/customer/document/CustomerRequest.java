package com.luxemart.customer.document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record CustomerRequest(

        String id,

        @NotNull(message = "First name should not be blank")
        String firstName,

        @NotNull(message = "Last name should not be blank")
        String lastName,

        @NotNull(message = "Email should not be blank")
        @Email(message = "Email is not valid one")
        String email,

        Address address
) {
}
