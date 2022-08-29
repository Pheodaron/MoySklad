package com.moysklad.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Store {
    private Long id;
    private String name;
    private List<Product> products;

    public Store(String name) {
        this.name = name;
    }
}
