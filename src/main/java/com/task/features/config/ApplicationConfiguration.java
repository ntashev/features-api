package com.task.features.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;

/**
 *  Application wide configuration.
 */
@Configuration
@EnableCaching
public class ApplicationConfiguration {

    public static final String FEATURES = "features";
    public static final String USERS = "users";

    /**
     * Creates a cache manager bean.
     *
     * @return cache manager bean
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(FEATURES), new ConcurrentMapCache(USERS)));
        return cacheManager;
    }

    /**
     * Creates PropertySourcesPlaceholderConfigurer bean.
     *
     * @return PropertySourcesPlaceholderConfigurer bean
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
