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
@Transactional(readOnly = true)
@CacheConfig(cacheManager = "cacheManager", cacheNames = {"storeCategories"}, keyGenerator = "CustomCacheKeyGenerator")
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory> implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public JpaRepository<StoreCategory, Long> getRepository() {
        return storeCategoryRepository;
    }

    @Override
    @Cacheable
    public StoreCategory getByName(String name) {
        return storeCategoryRepository.getByName(name);
    }

    @CacheEvict(cacheNames = {"storeCategories"}, allEntries = true)
    @Scheduled(cron = "0 0/2 22 * * ?") //start at 22:00 until 22.58
    public void evictCache() {
        logger.info("Evicting storeCategories cache contents.");
    }
}
