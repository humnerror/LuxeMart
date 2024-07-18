package com.luxemart.product.Exception;


public class ProductNotFoundException extends RuntimeException {


    public ProductNotFoundException(String msg){
        super(msg);
    }
}
