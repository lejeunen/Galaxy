// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.model.event;

//--------------------------------------------------------------------------------------------------------------------------------
public class Account
{
	private String accountIdentifier;
	private String status;

	//--------------------------------------------------------------------------------------------------------------------------------
	public String getAccountIdentifier()
	{
		return accountIdentifier;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setAccountIdentifier(String accountIdentifier)
	{
		this.accountIdentifier = accountIdentifier;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public String getStatus()
	{
		return status;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setStatus(String status)
	{
		this.status = status;
	}
}
