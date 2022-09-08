package com.moysklad.dto;

import com.moysklad.jook.tables.records.ProductsRecord;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Record6;

@Getter
@Setter
public class ProductDto {
    private String vendorCode;
    private String name;
    private Long count;
    private Long lastBuyCoast;
    private Long lastSellCoast;

    public ProductDto(Record6<Long,String,String,String,String,Long> record) {
        this.vendorCode = record.value4();
        this.name = record.value5();
        this.count = record.value6();
    }
}
