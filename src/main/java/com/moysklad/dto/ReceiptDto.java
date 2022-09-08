package com.moysklad.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptDto {
    @SerializedName("number")
    private Long number;
    @SerializedName("store")
    private Long storeId;
    @SerializedName("products")
    private List<ProductDto> productDto;

    @Getter
    @Setter
    @NoArgsConstructor
    public class ProductDto {
        private Long vendorCode;
        private String name;
        private Long count;
        private Long buyCost;
    }
}
