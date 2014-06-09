// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.service;

import com.cybernetix.appdirect.model.result.EventResult;

//--------------------------------------------------------------------------------------------------------------------------------
public interface EventHandler
{
	// account subscription management
	public EventResult subscriptionCreate(String eventUrl);
	public EventResult subscriptionChange(String eventUrl);
	public EventResult subscriptionCancel(String eventUrl);
	public EventResult subscriptionStatus(String eventUrl);

	// user access management
	public EventResult userAssign(String eventUrl);
	public EventResult userUnassign(String eventUrl);
}
