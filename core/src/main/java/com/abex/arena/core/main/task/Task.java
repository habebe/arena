package com.abex.arena.core.main.task;

import java.util.List;

import com.abex.arena.core.service.AccountService;
import com.abex.arena.core.service.PersistentObjectFactoryService;

public abstract class Task {
	private final String type;
	private final List<String> data;

	public Task(String type, List<String> data) {
		this.type = type;
		this.data = data;
	}

	public abstract String getTaskType();

	public abstract void run(PersistentObjectFactoryService persistentObjectFactoryService,
			AccountService accountService);

	public String getType() {
		return type;
	}

	public List<String> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Task (" + this.getTaskType() + ") [type=" + type + "," + this.data + "]";
	}

}
