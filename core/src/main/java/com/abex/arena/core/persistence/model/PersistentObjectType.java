package com.abex.arena.core.persistence.model;

import java.lang.reflect.Type;

public enum PersistentObjectType {
	PasswordResetToken("PasswordResetToken"), Privilege("Privilege"), User("User"), UserRole(
			"UserRole"), VerificationToken("VerificationToken");

	final String name;

	PersistentObjectType(String name) {
		this.name = name;
	}
	
	public static PersistentObjectType fromString(String name) {
		for (PersistentObjectType type : PersistentObjectType.values()) {
		      if (type.name.equalsIgnoreCase(name)) {
		        return type;
		      }
		    }
		throw new IllegalArgumentException("Unknown PersistentObjectType " + name);
	}

	public Type getPersistentClassType() {
		Type type;
		switch(this) {
		case PasswordResetToken:
			type = com.abex.arena.core.persistence.model.user.PasswordResetToken.class;
			break;
		case Privilege:
			type = com.abex.arena.core.persistence.model.user.Privilege.class;
			break;
		case User:
			type = com.abex.arena.core.persistence.model.user.User.class;
			break;
		case UserRole:
			type = com.abex.arena.core.persistence.model.user.UserRole.class;
			break;
		case VerificationToken:
			type = com.abex.arena.core.persistence.model.user.VerificationToken.class;
			break;
		default:
			throw new IllegalArgumentException("Unknown class Type " + this);
		}
		return type;
	}

}
