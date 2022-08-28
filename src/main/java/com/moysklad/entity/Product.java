package com.moysklad.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String vendorCode;
    private String name;
    private String lastBuyCoast;
    private String lastSellCoast;

    public Product(String vendorCode, String name, String lastBuyCoast, String lastSellCoast) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.lastBuyCoast = lastBuyCoast;
        this.lastSellCoast = lastSellCoast;
    }
}
