package com.moysklad.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Receipt {
    private Long number;
    private Store store;
    private List<Product> products;

    public Receipt(
            Long number,
            Store store,
            List<Product> products
    ) {
        this.number = number;
        this.store = store;
        this.products = products;
    }
}
