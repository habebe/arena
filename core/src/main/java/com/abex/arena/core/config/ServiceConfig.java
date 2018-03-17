package com.abex.arena.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abex.arena.core.service.AccountService;
import com.abex.arena.core.service.PersistentObjectFactoryService;
import com.abex.arena.core.service.impl.AccountServiceImpl;
import com.abex.arena.core.service.impl.PersistentObjectFactoryServiceImpl;

@Configuration
public class ServiceConfig {

	public ServiceConfig() {
		super();
	}

	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl();
	}

	@Bean
	public PersistentObjectFactoryService persistentObjectFactoryService() {
		return new PersistentObjectFactoryServiceImpl();
	}
	
}
