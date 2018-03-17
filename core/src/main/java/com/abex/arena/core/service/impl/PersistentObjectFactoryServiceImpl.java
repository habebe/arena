package com.abex.arena.core.service.impl;

import org.springframework.stereotype.Service;

import com.abex.arena.core.persistence.model.PersistentObject;
import com.abex.arena.core.persistence.model.PersistentObjectType;
import com.abex.arena.core.service.PersistentObjectFactoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service
public class PersistentObjectFactoryServiceImpl implements PersistentObjectFactoryService {

	private final Gson gson;

	public PersistentObjectFactoryServiceImpl() {
		this.gson = new GsonBuilder().create();
	}

	@Override
	public PersistentObject create(PersistentObjectType type, String jsonString) {
		return this.getGson().fromJson(jsonString, type.getPersistentClassType());
	}

	@Override
	public PersistentObject create(PersistentObjectType type, JsonObject json) {
		return this.getGson().fromJson(json, type.getPersistentClassType());
	}

	public Gson getGson() {
		return gson;
	}
}