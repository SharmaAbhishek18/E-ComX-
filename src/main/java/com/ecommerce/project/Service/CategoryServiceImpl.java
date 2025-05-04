package com.ecommerce.project.Service;

import com.ecommerce.project.Exception.APIException;
import com.ecommerce.project.Exception.myResourceNotFoundException;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;
import com.ecommerce.project.Repositories.CategoryRepository;
import com.ecommerce.project.Model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    //This method retrieves all categories, maps them to DTOs, wraps them in a
    //CategoryResponse, and returns it. It throws an exception if no categories exist.
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
     //if sortOrder is "asc" then sort in asc or else in desc order
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest .of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        //Fetching all the Categories from the database
        List<Category>categories =  categoryPage.getContent();
        if(categories.isEmpty())
            throw new APIException("NO Category Created till now ");
        List<CategoryDTO>categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }
    //Takes a Category Object as the input
    //Calls the save() method provided by categoryRepository
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {//add new Category to the database
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb  = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb != null){
            throw new APIException("Category with the name "+ category.getCategoryName() + " already Exist !!");
        }
        Category savedCategory  = categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

//
    @Override
    //Delete specific Category by its ID
    public CategoryDTO deleteCategory(Long categoryID) {
        //Fetches the specific category from the database.
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new myResourceNotFoundException("Category","categoryID",categoryID));
        categoryRepository.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    //Updates an existing category’s details.
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryID) {
        //Fetches all categories from the database.
        Category savedCategory = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new  myResourceNotFoundException("Category","categoryID",categoryID));
            Category category = modelMapper.map(categoryDTO,Category.class);
            category.setCategoryId(categoryID);
            savedCategory = categoryRepository.save(category);
            return modelMapper.map(savedCategory,CategoryDTO.class);
//        Optional<Category> optionalCategory = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryID))
//                .findFirst();
////        if the category exist we update it using the setCategoryName() Method
//        if (optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            // calls the save() to persist the updated category in the database
//            return categoryRepository.save(existingCategory);
//              }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not Found");
//        }
    }
}
