package com.luxemart.customer.document;

public record CustomerResponse(

        String id,

        String firstName,

        String lastName,

        String email,

        Address address

) {
}
