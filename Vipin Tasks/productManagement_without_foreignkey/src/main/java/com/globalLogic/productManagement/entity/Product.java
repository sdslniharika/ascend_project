package com.globalLogic.productManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Product")
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private double productPrice;
    private Integer categoryId;

    public Product(String productName, double productPrice, Integer categoryId){
        this.productName = productName;
        this.productPrice= productPrice;
        this.categoryId = categoryId;
    }
}
