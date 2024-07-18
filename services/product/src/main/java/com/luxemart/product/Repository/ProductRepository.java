package com.luxemart.product.Repository;

import com.luxemart.product.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findAllByIdInOrderById(List<Long> productIdPurchaseRequest);


}
