// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import com.cybernetix.service.UserService;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/v1/user")
public class UserResource
{
	@Autowired
	private UserService userService;

	//--------------------------------------------------------------------------------------------------------------------------------
	// Returns all users. Sample usage: curl http://localhost:8080/Galaxy/api/v1/user. Requires Admin role.
	//--------------------------------------------------------------------------------------------------------------------------------
	@PreAuthorize("hasRole('ROLE_ADMIN_GALAXY')")
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<User> loadAll()
	{
		return userService.loadAll();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/me", method = { RequestMethod.GET })
	@ResponseBody
	public User loadSelf()
	{
		return userService.getSelf();
	}
}
