// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.service;

import com.cybernetix.appdirect.model.event.Event;
import com.cybernetix.model.Company;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
public interface EventDataExtractor
{
	public Event fetchEvent(String eventUrl);

	public String getAccountIdentifier(Event event);
	public String getUserIdentifier(Event event);
	public String getEdition(Event event);
	public String getNotice(Event event);

	public Company getCompanyModel(Event event);

	public User getUserModel(Event event);
	public User getUserModelFromCreator(Event event);
}
