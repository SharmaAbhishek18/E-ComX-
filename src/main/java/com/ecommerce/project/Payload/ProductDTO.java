package com.ecommerce.project.Payload;

import com.ecommerce.project.Model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private String quantity;
    private double price;
    private double discount;
    private double specialPrice;
    private Long categoryId;
}
