package com.moysklad.dto;

import com.google.gson.annotations.SerializedName;
import com.moysklad.jook.tables.records.StoresRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StoreDto {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("products")
    private List<ProductDto> products;

    public StoreDto(Long id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public StoreDto(StoresRecord record) {
        this.id = record.getId();
        this.name = record.getName();
        this.products = new ArrayList<>();
    }

    public void addProduct(ProductDto product) {
        this.products.add(product);
    }

    public void addProduct(List<ProductDto> products) {
        this.products.addAll(products);
    }
}
