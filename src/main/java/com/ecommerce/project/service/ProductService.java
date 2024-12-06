package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;

public interface ProductService {


     ProductResponse searchByCategory(Long categoryId);

    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();
}
