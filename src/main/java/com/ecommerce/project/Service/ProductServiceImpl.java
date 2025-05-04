package com.ecommerce.project.Service;

import com.ecommerce.project.Exception.myResourceNotFoundException;
import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;
import com.ecommerce.project.Repositories.CategoryRepository;
import com.ecommerce.project.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Transactional
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryID, Product product) {
        Category category = categoryRepository.findById(categoryID)
            .orElseThrow(()->
                    new myResourceNotFoundException("Category","categoryID",categoryID));
        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice = product.getPrice() -
                (product.getDiscount()*0.01)*product.getPrice();
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct,ProductDTO.class);
    }
}
