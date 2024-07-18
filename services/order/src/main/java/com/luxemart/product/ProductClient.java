package com.luxemart.product;


import com.luxemart.exception.BusinessErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<ProductResponse> fetchAllProducts(List<ProductRequest> request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<List<ProductResponse>> entity = new ParameterizedTypeReference<>() {};

        HttpEntity<List<ProductRequest>> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<List<ProductResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                entity
        );

        if (responseEntity.getStatusCode().isError())
            throw new BusinessErrorException("An error occurred when fetching the orders from the product-service");

        return responseEntity.getBody();

    }


}
