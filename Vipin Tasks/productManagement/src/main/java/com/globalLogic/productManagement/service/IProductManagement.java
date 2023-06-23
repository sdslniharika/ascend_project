package com.globalLogic.productManagement.service;

import com.globalLogic.productManagement.dto.CategoryDTO;
import com.globalLogic.productManagement.dto.ProductDTO;
import com.globalLogic.productManagement.entity.Category;
import com.globalLogic.productManagement.entity.Product;

import java.util.List;

public interface IProductManagement {
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    ProductDTO getProductById(Integer id);
    List<ProductDTO> getAllProducts();
    List<CategoryDTO> getAllCategories();
    List<ProductDTO> findProductsByName(String name);
    List<ProductDTO> findProductsByCategory(String categoryName);
    String deleteProductByID(Integer id);
}
