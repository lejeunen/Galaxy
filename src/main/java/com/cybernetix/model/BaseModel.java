// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

//--------------------------------------------------------------------------------------------------------------------------------
@MappedSuperclass
public abstract class BaseModel
{
	@Id
	@SequenceGenerator(name = "seqGenerator", sequenceName = "key_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator")
	@Column
	private Long primaryKey;
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public Long getPrimaryKey()
	{
		return primaryKey;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPrimaryKey(Long primaryKey)
	{
		this.primaryKey = primaryKey;
	}
}
