package com.globalLogic.productManagement.util;

import com.globalLogic.productManagement.dto.CategoryDTO;
import com.globalLogic.productManagement.dto.ProductDTO;
import com.globalLogic.productManagement.entity.Category;
import com.globalLogic.productManagement.entity.Product;
import com.globalLogic.productManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    @Autowired
    CategoryRepository categoryRepository;
    public CategoryDTO toCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO(category.getCategoryId(), category.getCategoryName());
        return categoryDTO;
    }

    public ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO(product.getProductId(), product.getProductName(), product.getProductPrice(), toCategoryDTO(categoryRepository.findById(product.getCategoryId()).get()));
        return productDTO;
    }

    public List<ProductDTO> toProductDTOSList(List<Product> products){
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products){
            ProductDTO productDTO = new ProductDTO(product.getProductId(), product.getProductName(), product.getProductPrice(), toCategoryDTO(categoryRepository.findById(product.getCategoryId()).get()));

            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public List<CategoryDTO> toCategoryDTOSList(List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO categoryDTO = new CategoryDTO(category.getCategoryId(), category.getCategoryName());

            categoryDTOS.add(categoryDTO);
        }

        return categoryDTOS;
    }
}
