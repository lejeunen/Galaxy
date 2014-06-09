// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.model.event;

//--------------------------------------------------------------------------------------------------------------------------------
public class Payload
{
	private Company company; // only applicable for 'create' events
	private Account account; // only applicable for 'change', 'cancel' and 'status' events
	private Order order; //only applicable for 'create' and 'change' events
	private Notice notice; // only applicable for 'status' events
	private User user; // only applicable for 'user_assisg' and 'user_unassign' events

	//--------------------------------------------------------------------------------------------------------------------------------
	public Company getCompany()
	{
		return company;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setCompany(Company company)
	{
		this.company = company;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public Account getAccount()
	{
		return account;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setAccount(Account account)
	{
		this.account = account;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public Order getOrder()
	{
		return order;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setOrder(Order order)
	{
		this.order = order;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public Notice getNotice()
	{
		return notice;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setNotice(Notice notice)
	{
		this.notice = notice;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public User getUser()
	{
		return user;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setUser(User user)
	{
		this.user = user;
	}
}
