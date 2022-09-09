package com.moysklad.service.store;

import com.moysklad.auxilary.DataSource;
import com.moysklad.dto.StoreDto;
import com.moysklad.jook.tables.Products;
import com.moysklad.jook.tables.ProductsStores;
import com.moysklad.jook.tables.Stores;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.jsonObject;

public class StoreRepositoryImpl implements StoreRepository{
    @Override
    public Optional<StoreDto> getStoreById(Long storeId) {
        try (Connection connection = DataSource.getConnection()) {
            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);

            var store = getStoresRawQuery(ctx).where(Stores.STORES.ID.eq(storeId)).fetchOneInto(StoreDto.class);

            return Optional.ofNullable(store);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<StoreDto> findAll() {
        try (Connection connection = DataSource.getConnection()) {
            DSLContext ctx = DSL.using(connection, SQLDialect.POSTGRES);

            return getStoresRawQuery(ctx).fetchInto(StoreDto.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private SelectJoinStep<Record3<Long, String, JSON>> getStoresRawQuery(DSLContext ctx) {
        return ctx.select(
                        Stores.STORES.ID,
                        Stores.STORES.NAME,
                        field(
                                select(jsonArrayAgg(jsonObject(
                                        Products.PRODUCTS.NAME,
                                        Products.PRODUCTS.VENDOR_CODE,
                                        ProductsStores.PRODUCTS_STORES.COUNT
                                ))).from(ProductsStores.PRODUCTS_STORES)
                                        .join(Products.PRODUCTS)
                                        .on(ProductsStores.PRODUCTS_STORES.PRODUCT_ID.eq(Products.PRODUCTS.ID))
                                        .where(ProductsStores.PRODUCTS_STORES.STORE_ID.eq(Stores.STORES.ID))
                        ).as("products")
                )
                .from(Stores.STORES);
    }
}
