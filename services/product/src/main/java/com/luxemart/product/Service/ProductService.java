package com.luxemart.product.Service;


import com.luxemart.product.Exception.ProductInSufficientException;
import com.luxemart.product.Exception.ProductNotFoundException;
import com.luxemart.product.Product.*;
import com.luxemart.product.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProductMapper mapper;

    public Long createProduct(ProductRequest request) {
        return mapper.toProduct(request).getId();
    }

    public ProductResponse findProductById(Long id) {
        return mapper.toProductResponse(repository.
                findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Given product id is invalid or not available in the storage")));
    }

    public List<ProductPurchaseResponse> purchaseProductRequest(List<ProductPurchaseRequest> request) {

        List<Long> productIdPurchaseRequest = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIdPurchaseRequest);

        if (storedProducts.size() != productIdPurchaseRequest.size()) {
            throw new EntityNotFoundException("One or more products are not available in the storage");
        }

        var purchaseResponse = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {

            if (storedProducts.get(i).getAvailableQuantity() < request.get(i).Quantity()) {
                throw new ProductInSufficientException("One of the product is not in stock in the storage, Please check later");
            }

            double newAvailableQuantity = storedProducts.get(i).getAvailableQuantity() - request.get(i).Quantity();
            storedProducts.get(i).setAvailableQuantity(newAvailableQuantity);
            repository.save(storedProducts.get(i));
            purchaseResponse.add(mapper.toProductPurchaseResponse(storedProducts.get(i), request.get(i)));
        }

        return purchaseResponse;
    }
}
