package com.abex.arena.core.service;

import com.abex.arena.core.persistence.model.PersistentObject;
import com.abex.arena.core.persistence.model.PersistentObjectType;
import com.google.gson.JsonObject;

public interface PersistentObjectFactoryService {

	PersistentObject create(PersistentObjectType type, String json);

	PersistentObject create(PersistentObjectType type, JsonObject json);
}
