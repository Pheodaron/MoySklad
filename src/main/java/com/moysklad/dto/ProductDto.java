package com.moysklad.dto;

import com.google.gson.annotations.SerializedName;
import com.moysklad.jook.tables.records.ProductsRecord;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Record2;

@Getter
@Setter
public class ProductDto {
    @SerializedName("vendorCode")
    private Long vendorCode;
    @SerializedName("name")
    private String name;
    @SerializedName("count")
    private Long count;
    @SerializedName("lastBuyCoast")
    private Long lastBuyCoast;
    @SerializedName("lastSellCoast")
    private Long lastSellCoast;

    public ProductDto(Long vendorCode, String name, Long count) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.count = count;
    }

    public ProductDto(Record2<ProductsRecord, Long> parentRecord) {
        var record = parentRecord.component1();
        this.vendorCode = record.getVendorCode();
        this.name = record.getName();
        this.count = parentRecord.component2();
        this.lastBuyCoast = null;
        this.lastSellCoast = null;
    }

    public ProductDto(ProductsRecord record, Long count) {
        this.vendorCode = record.getVendorCode();
        this.name = record.getName();
        this.count = count;
        this.lastBuyCoast = null;
        this.lastSellCoast = null;
    }
}
