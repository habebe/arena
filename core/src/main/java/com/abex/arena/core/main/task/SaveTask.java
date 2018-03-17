package com.abex.arena.core.main.task;

import java.util.List;

import com.abex.arena.core.persistence.model.PersistentObject;
import com.abex.arena.core.persistence.model.PersistentObjectType;
import com.abex.arena.core.persistence.model.user.User;
import com.abex.arena.core.service.AccountService;
import com.abex.arena.core.service.PersistentObjectFactoryService;

public class SaveTask extends Task {
	public SaveTask(String type, List<String> data) {
		super(type, data);
	}

	@Override
	public String getTaskType() {
		return "Save";
	}

	@Override
	public void run(PersistentObjectFactoryService persistentObjectFactoryService, AccountService accountService) {
		PersistentObjectType persistentObjectType = PersistentObjectType.fromString(this.getType());
		PersistentObject entity = persistentObjectFactoryService.create(persistentObjectType, this.getData().get(0));

		switch (persistentObjectType) {
		case PasswordResetToken:
		//	accountService.getPasswordResetTokenRepository().save((PasswordResetToken) entity);
			break;
		case Privilege:
		//	accountService.getPrivilegeRepository().save((Privilege) entity);
			break;
		case User:
			accountService.getUserRepository().save((User) entity);
			break;
		case UserRole:
		//	accountService.getUserRoleRepository().save((UserRole) entity);
			break;
		case VerificationToken:
		//	accountService.getVerificationTokenTokenRepository().save((VerificationToken) entity);
			break;
		default:
			break;

		}
	}
}
