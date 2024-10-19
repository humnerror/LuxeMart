package com.luxemart.customer.service;


import com.luxemart.customer.document.Customer;
import com.luxemart.customer.document.CustomerRequest;
import com.luxemart.customer.document.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {


    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
