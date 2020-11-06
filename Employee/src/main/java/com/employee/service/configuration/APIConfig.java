package com.employee.service.configuration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching

public class APIConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		return super.cacheManager();
	}

	@Bean
	public net.sf.ehcache.CacheManager ehcachemanager() {
		CacheConfiguration config = new CacheConfiguration();
		config.setName("short-term-cache");
		config.setMemoryStoreEvictionPolicy("LFU");
		config.internalSetMaxEntriesInCache(1000);
		config.setMaxEntriesLocalDisk(1000);
		config.internalSetTimeToLive(10);
		return net.sf.ehcache.CacheManager.newInstance();

	}
}
