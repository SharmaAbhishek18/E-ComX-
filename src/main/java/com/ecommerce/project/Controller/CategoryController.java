package com.ecommerce.project.Controller;

import com.ecommerce.project.Config.AppConst;
import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;
import com.ecommerce.project.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //   public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "PageNumber",defaultValue = AppConst.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(name = "PageSize",defaultValue = AppConst.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(name = "SortBy",defaultValue = AppConst.SORT_CATEGORIES_BY,required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConst.SORT_DIR,required = false) String sortOrder){
        CategoryResponse CategoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(CategoryResponse,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid  @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedcategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedcategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryID}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryID) {
            CategoryDTO deleteCategory = categoryService.deleteCategory(categoryID);
            return new ResponseEntity<>(deleteCategory, HttpStatus.OK);

    }
    @PutMapping ("/public/categories/{categoryID}")
    public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                     @PathVariable Long categoryID){
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryID);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);

    }
}


