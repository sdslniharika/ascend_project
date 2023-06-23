package com.globalLogic.productManagement.validator;

import com.globalLogic.productManagement.dto.ProductDTO;
import com.globalLogic.productManagement.entity.Category;
import com.globalLogic.productManagement.entity.Product;
import com.globalLogic.productManagement.exceptions.CategoryNotFoundException;
import com.globalLogic.productManagement.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Validate {
    public void validateProductId(Integer id){
        if(id<0){
            throw new ProductNotFoundException("Product ID cannot be 0 or less than 0");
        }
    }

    public void validateProductDTO(ProductDTO productDTO){
        if(productDTO.getProductName().isBlank()){
            throw new ProductNotFoundException("Product Name cannot be Blank or Empty");
        }
        else if(productDTO.getCategory()==null){
            throw new CategoryNotFoundException("Category cannot be null");
        }
        else if(productDTO.getProductPrice()<0){
            throw new ProductNotFoundException("Price cannot be less than 0");
        }
    }

    public void validateProductName(String name){
        if(name.isBlank()){
            throw new ProductNotFoundException("Product Name cannot be Blank or Empty");
        }
    }

    public void validateCategoryId(Integer id){
        if(id<0){
            throw new CategoryNotFoundException("Category ID cannot be 0 or less than 0");
        }
    }

    public void validateCategoryName(String name){
        if(name.isBlank()){
            throw new CategoryNotFoundException("Category Name cannot be Blank or Empty");
        }
    }

    public void validateProductList(List<Product> productList){
        if(productList.isEmpty()){
            throw new ProductNotFoundException("No Products are available at the moment");
        }
    }

    public void validateCategoryList(List<Category> categories){
        if(categories.isEmpty()){
            throw new CategoryNotFoundException("No Category are available at the moment");
        }
    }

    public void validateOptionalProduct(Optional<Product> optionalProduct){
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("No Product Found with the mentioned details");
        }
    }

    public void validateOptionalCategory(Optional<Category> optionalCategory){
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("No Category Found with the mentioned details");
        }
    }
}
