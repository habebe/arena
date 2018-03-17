package com.abex.arena.core.main.task;

import java.util.List;

import com.abex.arena.core.persistence.model.PersistentObject;
import com.abex.arena.core.persistence.model.PersistentObjectType;
import com.abex.arena.core.service.AccountService;
import com.abex.arena.core.service.PersistentObjectFactoryService;

public class DeleteTask extends Task {
	public DeleteTask(String type, List<String> data) {
		super(type,data);
	}

	@Override
	public String getTaskType() {
		return "Delete";
	}

	@Override
	public void run(PersistentObjectFactoryService persistentObjectFactoryService,AccountService accountService) {
		PersistentObjectType persistentObjectType = PersistentObjectType.fromString(this.getType());
		PersistentObject object = persistentObjectFactoryService.create(persistentObjectType,this.getData().get(0));
		
		switch(persistentObjectType) {
		case PasswordResetToken:
			break;
		case Privilege:
			break;
		case User:
			break;
		case UserRole:
	//		accountService.getUserRoleRepository().delete(object.getId());
			break;
		case VerificationToken:
			break;
		default:
			break;
				
		}
		
		System.out.println(object);
		
		
	}
}
