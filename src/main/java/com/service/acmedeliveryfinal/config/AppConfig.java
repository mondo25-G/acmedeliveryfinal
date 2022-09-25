package com.service.acmedeliveryfinal.config;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("com.service.acmedeliveryfinal.service")
@EnableCaching
public class AppConfig  {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //We are going to assume that the declared caches that concern most popular products/stores
        //based on some parameters defined in the services are to be updated once per week
        //This update/flush functionality will not be included.
        Cache cashPopularStores = new ConcurrentMapCache("stores"); //Cache stored as Map
        Cache cashPopularProducts = new ConcurrentMapCache("products"); //Cache stored as Map
        cacheManager.setCaches(Arrays.asList(cashPopularStores, cashPopularProducts));
        return cacheManager;
    }
}