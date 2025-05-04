package com.ecommerce.project.Service;

import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;
import org.springframework.stereotype.Service;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryID);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryID);
}
