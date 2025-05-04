package com.ecommerce.project.Controller;

import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;
import com.ecommerce.project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/admin/categories/{categoryID}/product")
    public ResponseEntity<ProductDTO>addProduct(@RequestBody Product product,
                                                @PathVariable Long categoryID){
       ProductDTO productDTO =  productService.addProduct( categoryID,product);
       return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
}
