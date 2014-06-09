// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.appdirect.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import com.cybernetix.appdirect.model.result.Error;
import com.cybernetix.appdirect.model.result.EventResult;
import com.cybernetix.appdirect.model.result.EventResultException;
import com.cybernetix.appdirect.service.EventHandler;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/v1/appDirect")
public class EventController
{
	@Autowired
	EventHandler subscriptionService;
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user buys our application from AppDirect.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionCreate", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionCreate(@RequestParam final String eventUrl)
	{
		return subscriptionService.subscriptionCreate(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user upgrades/downgrades/modifies an existing subscription.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionChange", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionChange(@RequestParam final String eventUrl)
	{
		return subscriptionService.subscriptionChange(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user cancels his subscription to our application.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionCancel", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionCancel(@RequestParam final String eventUrl)
	{
		return subscriptionService.subscriptionCancel(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a subscription goes overdue or delinquent.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionStatus", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionStatus(@RequestParam final String eventUrl)
	{
		return subscriptionService.subscriptionStatus(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user assigns a user to our application.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/userAssign", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult userAssign(@RequestParam final String eventUrl)
	{
		return subscriptionService.userAssign(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user unassigns a user from our application.
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/userUnassign", params = "eventUrl", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult userUnassign(@RequestParam final String eventUrl)
	{
		return subscriptionService.userUnassign(eventUrl);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// exception handling for 'planned' exceptions. Always returned using properly formatted XML and HTTP 200.
	//--------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(EventResultException.class)
	@ResponseBody
	public EventResult eventResultExceptionHandler(EventResultException exception)
	{
		return EventResult.error(exception);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// exception handling for all other 'unplanned' exceptions. Always returned using properly formatted XML and HTTP 200.
	//--------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(/* all other exception types */)
	@ResponseBody
	public EventResult unhandledExceptionHandler(Exception exception)
	{
		return EventResult.errorLogged(Error.UNKNOWN_ERROR,
			"An unexpected error occurred. If the problem persists, please contact a system administrator.", exception);
	}
}
