package com.moysklad.service;

import com.moysklad.auxilary.DataSource;
import com.moysklad.dto.ReceiptDto;
import com.moysklad.dto.StoreDto;
import com.moysklad.entity.Product;
import com.moysklad.entity.Store;
import com.moysklad.jook.tables.Products;
import com.moysklad.jook.tables.ProductsStores;
import com.moysklad.jook.tables.Stores;
import com.moysklad.jook.tables.records.ProductsRecord;
import com.moysklad.jook.tables.records.StoresRecord;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentsRepositoryImpl implements DocumentsRepository {

    @Override
    public String readBody(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        return buffer.toString();
    }

    @Override
    public void addReceiptToDatabase(ReceiptDto dto) {
//        try (Connection connection = DataSource.getConnection()) {
//            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);
//            if (ctx.fetchExists(Stores.STORES, Stores.STORES.ID.eq(dto.getStoreId()))) {
//                ReceiptsRecord receiptsRecord = ctx.newRecord(Receipts.RECEIPTS);
//                receiptsRecord.setId(dto.getNumber());
//                receiptsRecord.setStoreId(dto.getStoreId());
//                receiptsRecord.store();
//
//                dto.getProductDto().forEach(product -> {
////                    if (ctx.fetchExists(Products.PRODUCTS, Products.PRODUCTS.VENDOR_CODE.eq(product.getVendorCode()))) {
////                        ReceiptsProductsRecord receiptsProducts = ctx.newRecord(ReceiptsProducts.RECEIPTS_PRODUCTS);
////                        receiptsProducts.setProductId(product.)
////                    }
//                });
//
//                if (ctx.fetchExists(Products.PRODUCTS, Products.PRODUCTS.VENDOR_CODE.eq(dto.getProductDto().)))
//
////                ReceiptsProductsRecord receiptsProductsRecord = ctx.newRecord(ReceiptsProducts.RECEIPTS_PRODUCTS);
////                receiptsProductsRecord.set
//            }
//
//        } catch (SQLException e) {
//
//        }
    }

    @Override
    public void writeBody(HttpServletResponse resp, String responseBody) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(responseBody);
        out.flush();
    }

    @Override
    public void addProductToDatabase(Product product, Long storeId) {
        try (Connection connection = DataSource.getConnection()) {
            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);
            if (ctx.fetchExists(Stores.STORES, Stores.STORES.ID.eq(storeId))) {
                ProductsRecord newProduct = ctx.newRecord(Products.PRODUCTS);
                newProduct.setName(product.getName());
//                newProduct.setVendorCode(product.getVendorCode());
                newProduct.setLastBuyCost(product.getLastBuyCoast());
                newProduct.setLastSellCost(product.getLastSellCoast());
                newProduct.store();
            } else {
                System.out.println("Throw exception that store not exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStoreToDatabase(Store store) {
        try (Connection connection = DataSource.getConnection()) {
            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);
            StoresRecord newStore = ctx.newRecord(Stores.STORES);
            newStore.setName(store.getName());
            newStore.store();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StoreDto> getAllStoresFromDatabase() {
        try (Connection connection = DataSource.getConnection()) {
            List<StoreDto> stores = new ArrayList<>();
            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);
            var productRecords =
                    ctx.select(Products.PRODUCTS)
                    .from(Stores.STORES)
                    .join(ProductsStores.PRODUCTS_STORES)
                    .on(ProductsStores.PRODUCTS_STORES.STORE_ID.eq(Stores.STORES.ID))
                    .join(Products.PRODUCTS)
                    .on(ProductsStores.PRODUCTS_STORES.PRODUCT_ID.eq(Products.PRODUCTS.ID))
                    .fetchGroups(Stores.STORES);

            var keySet = productRecords.keySet();
            for (StoresRecord record : keySet) {
                var test = productRecords.get(record);
            }
            return stores;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
//        try (Connection connection = DataSource.getConnection()) {
//            List<StoreDto> stores = new ArrayList<>();
//            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);
//            // TODO: 29.08.2022 Add transaction
//            var productRecords = ctx.select(Stores.STORES.ID, Stores.STORES.NAME, Products.PRODUCTS.VENDOR_CODE, Products.PRODUCTS.VENDOR_CODE, Products.PRODUCTS.NAME, ProductsStores.PRODUCTS_STORES.COUNT)
//                    .from(Stores.STORES)
//                    .join(ProductsStores.PRODUCTS_STORES)
//                    .on(ProductsStores.PRODUCTS_STORES.STORE_ID.eq(Stores.STORES.ID))
//                    .join(Products.PRODUCTS)
//                    .on(ProductsStores.PRODUCTS_STORES.PRODUCT_VENDOR_CODE.eq(Products.PRODUCTS.VENDOR_CODE))
//                    .fetchGroups(Stores.STORES.ID);
//
//            var keySet = productRecords.keySet();
//            for (Long key : keySet) {
//                var results = productRecords.get(key);
//                StoreDto store = new StoreDto(key, results.get(0).component2());
//                stores.add(store);
//                results.forEach(row -> {
//                    store.addProduct(new ProductDto(row));
//                });
//            }
//            return stores;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
    }
}
