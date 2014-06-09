// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// --------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "tCompany")
public class Company extends BaseModel
{
	@Basic
	@Column(unique = true)
	private String name;

	@Basic
	@Column
	private String edition;

	@Basic
	@Column
	private String notice;

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setName(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getEdition()
	{
		return edition;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setEdition(String edition)
	{
		this.edition = edition;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getNotice()
	{
		return notice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setNotice(String notice)
	{
		this.notice = notice;
	}
}
