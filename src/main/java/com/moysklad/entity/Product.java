package com.moysklad.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String vendorCode;
    private String name;
    private Long lastBuyCoast;
    private Long lastSellCoast;

    public Product(
            String vendorCode,
            String name,
            Long lastBuyCoast,
            Long lastSellCoast
    ) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.lastBuyCoast = lastBuyCoast;
        this.lastSellCoast = lastSellCoast;
    }
}
