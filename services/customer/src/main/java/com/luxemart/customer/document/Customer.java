package com.luxemart.customer.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@Getter
@Setter
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

}
