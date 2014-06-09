// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.service.impl;

import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;
import com.cybernetix.appdirect.model.event.Event;
import com.cybernetix.appdirect.model.result.Error;
import com.cybernetix.appdirect.model.result.EventResultException;
import com.cybernetix.appdirect.service.EventDataExtractor;
import com.cybernetix.model.Company;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Service("appDirectEventExtractor")
public class EventDataExtractorImpl implements EventDataExtractor
{
	private OAuthRestTemplate restTemplate;

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Event fetchEvent(String eventUrl)
	{
		try
		{
			return restTemplate.getForEntity(eventUrl, Event.class).getBody();
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, String.format("Error fetching event @ %s.", eventUrl));
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getAccountIdentifier(Event event)
	{
		try
		{
			return event.getPayload().getAccount().getAccountIdentifier();
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting account identifier from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getUserIdentifier(Event event)
	{
		try
		{
			return event.getPayload().getUser().getOpenId();
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting user identifier from event.");
		}

	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getEdition(Event event)
	{
		try
		{
			return event.getPayload().getOrder().getEditionCode();
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting edition from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getNotice(Event event)
	{
		try
		{
			return event.getPayload().getNotice().getType();
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting notice from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Company getCompanyModel(Event event)
	{
		try
		{
			Company company = new Company();
			company.setName(event.getPayload().getCompany().getName());
			company.setEdition(event.getPayload().getOrder().getEditionCode());

			return company;
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting company details from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public User getUserModel(Event event)
	{
		try
		{
			User user = new User();
			user.setEmail(event.getPayload().getUser().getEmail());
			user.setFirstName(event.getPayload().getUser().getFirstName());
			user.setLastName(event.getPayload().getUser().getLastName());
			user.setOpenId(event.getPayload().getUser().getOpenId());
	
			return user;
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting user details from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public User getUserModelFromCreator(Event event)
	{
		try
		{
			User user = new User();
			user.setEmail(event.getCreator().getEmail());
			user.setFirstName(event.getCreator().getFirstName());
			user.setLastName(event.getCreator().getLastName());
			user.setOpenId(event.getCreator().getOpenId());
	
			return user;
		}
		catch(Throwable e)
		{
			throw new EventResultException(Error.INVALID_RESPONSE, "Error extracting creator details from event.");
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public OAuthRestTemplate getRestTemplate()
	{
		return restTemplate;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setRestTemplate(OAuthRestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
}
