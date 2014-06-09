// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

//--------------------------------------------------------------------------------------------------------------------------------
public enum Role
{
	USER ("ROLE_USER"),
	OAUTH_APPDIRECT ("ROLE_OAUTH_APPDIRECT"),
	ADMIN ("ROLE_ADMIN_GALAXY");

	final private String string;

	//--------------------------------------------------------------------------------------------------------------------------------
	private Role(String string)
	{
		this.string = string;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public String toString()
	{
		return string;
	}
}
