package com.moysklad.dto;

import com.moysklad.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StoreDto {
    private Long id;
    private String name;
    private List<ProductDto> products;

    public StoreDto(Long id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(ProductDto product) {
        this.products.add(product);
    }
}
