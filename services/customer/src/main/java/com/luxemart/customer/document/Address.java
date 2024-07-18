package com.luxemart.customer.document;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Address {

    private String street;
    private String city;
    private String zip;

}
