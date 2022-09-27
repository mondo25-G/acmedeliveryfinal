package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.ProductCategory;
import com.service.acmedeliveryfinal.repository.ProductCategoryRepository;
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
@CacheConfig(cacheManager = "cacheManager", cacheNames = {"productCategories"}, keyGenerator = "CustomCacheKeyGenerator")
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public JpaRepository<ProductCategory, Long> getRepository() {
        return productCategoryRepository;
    }

    @Override
    @Cacheable
    public ProductCategory getByName(String name) {
        return productCategoryRepository.getByName(name);
    }

    @CacheEvict(cacheNames = {"productCategories"}, allEntries = true)
    @Scheduled(cron = "0 0/2 22 * * ?") //start at 22:00 until 22.58
    public void evictCache() {
        logger.info("Evicting productCategories cache contents.");
    }
}
