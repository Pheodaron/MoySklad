package com.moysklad.service;

import com.moysklad.dto.ReceiptDto;
import com.moysklad.dto.StoreDto;
import com.moysklad.entity.Product;
import com.moysklad.entity.Store;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface DocumentsRepository {
    String readBody(HttpServletRequest req) throws IOException;

    void addStoreToDatabase(Store store);

    List<StoreDto> getAllStoresFromDatabase();

    void addProductToDatabase(Product product, Long storeId);

    void addReceiptToDatabase(ReceiptDto dto);

    void writeBody(HttpServletResponse resp, String responseBody) throws IOException;
}
