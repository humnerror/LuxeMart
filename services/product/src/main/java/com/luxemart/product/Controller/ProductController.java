package com.luxemart.product.Controller;


import com.luxemart.product.Product.ProductPurchaseRequest;
import com.luxemart.product.Product.ProductPurchaseResponse;
import com.luxemart.product.Product.ProductRequest;
import com.luxemart.product.Product.ProductResponse;
import com.luxemart.product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping()
    public ResponseEntity<Long> createProduct(
            @RequestBody @Valid ProductRequest request
            ){

        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findByProductId(
            @PathVariable("productId") Long id
    ){
        return ResponseEntity.ok(service.findProductById(id));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseChosenProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> request
            ){
        return ResponseEntity.ok(service.purchaseProductRequest(request));
    }



}
