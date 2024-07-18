package com.luxemart.customer.controller;


import com.luxemart.customer.document.CustomerRequest;
import com.luxemart.customer.document.CustomerResponse;
import com.luxemart.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class registerController {

    private final CustomerService service;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(service.createUser(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> UpdateUser(@RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAllUsers(){
        List<CustomerResponse> allCustomers = service.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAUser(@PathVariable("id")String id){
        service.deleteAUser(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> existOrNotById(@PathVariable("id")String id){
        return ResponseEntity.ok(service.exist(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponse> findWithId(@PathVariable("id")String id){
        return ResponseEntity.ok(service.find(id));
    }


}
