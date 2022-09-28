package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory> implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public JpaRepository<StoreCategory, Long> getRepository() {
        return storeCategoryRepository;
    }

    @Override
    public StoreCategory getByName(String name) {
        return storeCategoryRepository.getByName(name);
    }

}
