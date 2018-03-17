package com.abex.arena.core.persistence.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentObject {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	public PersistentObject(){
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
