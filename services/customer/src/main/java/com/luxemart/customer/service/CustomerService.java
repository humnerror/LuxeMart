package com.luxemart.customer.service;

import com.luxemart.customer.document.Customer;
import com.luxemart.customer.document.CustomerRequest;
import com.luxemart.customer.document.CustomerResponse;
import com.luxemart.customer.exception.CustomerNotFoundException;
import com.luxemart.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public String createUser(CustomerRequest request) {
        Customer customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = repository.findById(request.id()).
                orElseThrow(() ->
                        new CustomerNotFoundException(
                                "cannot update customer, customer is not found in the DB"));

        mergeCustomer(customer, request);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll().stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    public void deleteAUser(String id){
        Customer customer = repository.findById(id).get();
        repository.delete(customer);
    }


    public Boolean exist(String id) {

        return repository.findById(id)
                .isPresent();
    }

    public CustomerResponse find(String id) {

        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(
                        "Customer "+id+" is not found"
                ));
    }
}
