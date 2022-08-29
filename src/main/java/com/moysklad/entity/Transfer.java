package com.moysklad.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Transfer {
    private Long number;
    private Store from;
    private Store to;
    private List<Product> products;

    public Transfer(
            Long number,
            Store from,
            Store to,
            List<Product> products
    ) {
        this.number = number;
        this.from = from;
        this.to = to;
        this.products = products;
    }
}
