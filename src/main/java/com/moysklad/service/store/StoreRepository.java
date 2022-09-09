package com.moysklad.service.store;

import com.moysklad.dto.StoreDto;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Optional<StoreDto> getStoreById(Long storeId);

    List<StoreDto> findAll();
}
