package com.ecommerce.project.Service;

import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;
import jakarta.transaction.Transactional;

public interface ProductService {

    ProductDTO addProduct(Long categoryID, Product product);
}
