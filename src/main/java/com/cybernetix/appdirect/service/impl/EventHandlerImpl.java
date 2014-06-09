// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cybernetix.appdirect.model.event.Event;
import com.cybernetix.appdirect.model.result.Error;
import com.cybernetix.appdirect.model.result.EventResult;
import com.cybernetix.appdirect.model.result.EventResultException;
import com.cybernetix.appdirect.service.EventDataExtractor;
import com.cybernetix.appdirect.service.EventHandler;
import com.cybernetix.service.CompanyService;
import com.cybernetix.service.UserService;
import com.cybernetix.model.Company;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Service
public class EventHandlerImpl implements EventHandler
{
	@Autowired
	private EventDataExtractor eventDataExtractor;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult subscriptionCreate(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract company from event
		Company company = eventDataExtractor.getCompanyModel(event);

		// extract user from event
		User user = eventDataExtractor.getUserModelFromCreator(event);

		// persist company/user pair
		saveCompanyUser(company, user);

		// use company key as accountIdentifier
		return EventResult.success(company.getPrimaryKey().toString());
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult subscriptionChange(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract account identifier from event
		String accountIdentifier = eventDataExtractor.getAccountIdentifier(event);

		// extract edition from event
		String edition = eventDataExtractor.getEdition(event);

		// load company record from database
		Company company = loadCompany(accountIdentifier);

		// set edition for company
		company.setEdition(edition);

		// persist company update
		companyService.save(company);

		return EventResult.success();
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult subscriptionCancel(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract account identifier from event
		String accountIdentifier = eventDataExtractor.getAccountIdentifier(event);

		// persist company delete
		companyService.delete(loadCompany(accountIdentifier));

		return EventResult.success();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult subscriptionStatus(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract company key from event
		String accountIdentifier = eventDataExtractor.getAccountIdentifier(event);

		// extract notice from event
		String notice = eventDataExtractor.getNotice(event);

		// load company record from database
		Company company = loadCompany(accountIdentifier);

		// update notice for company
		company.setNotice(notice);

		// persist company update
		companyService.save(company);

		return EventResult.success();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult userAssign(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract company key from event
		String accountIdentifier = eventDataExtractor.getAccountIdentifier(event);

		// extract user from event
		User user = eventDataExtractor.getUserModel(event);

		// load company record from database
		Company company = loadCompany(accountIdentifier);
		
		// assign user to company
		user.setCompany(company);

		// persist user/company assignment
		saveCompanyUser(company, user);

		return EventResult.success();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EventResult userUnassign(String eventUrl)
	{
		// fetch event from AppDirect
		Event event = eventDataExtractor.fetchEvent(eventUrl);

		// extract user identifier from event
		String userIdentifier = eventDataExtractor.getUserIdentifier(event);

		// delete user
		userService.delete(loadUser(userIdentifier));

		return EventResult.success();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	protected Company loadCompany(String accountIdentifier)
	{
		Company company = companyService.loadByPrimaryKey(Long.valueOf(accountIdentifier));
		if (company == null)
		{
			throw new EventResultException(Error.ACCOUNT_NOT_FOUND,
				String.format("Account doesn't exist @ %s.", accountIdentifier));
		}

		return company;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	protected User loadUser(String userIdentifier)
	{
		User user = userService.loadUserByUsername(userIdentifier);
		if (user == null)
		{
			throw new EventResultException(Error.USER_NOT_FOUND,
				String.format("User doesn't exist @ %s - Operation cancelled.", userIdentifier));
		}

		return user;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	protected void saveCompanyUser(Company company, User user)
	{
		try
		{
			// persist company/user pair within single transaction
			companyService.save(company, user);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new EventResultException(Error.USER_ALREADY_EXISTS,
				String.format("User already exists @ %s - %s - Operation cancelled.", company.getName(), user.getEmail()));
		}
	}
}
