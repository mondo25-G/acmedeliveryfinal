package com.service.acmedeliveryfinal.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean("CustomCacheKeyGenerator")
    public KeyGenerator getCacheKeyGenerator() {
        return new CustomKeyGenerator();
    }

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache cachePopularReports = new ConcurrentMapCache("popular");
        cacheManager.setCaches(Arrays.asList(cachePopularReports));
        return cacheManager;
    }
}