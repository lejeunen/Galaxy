// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cybernetix.service.GalaxyService;
import com.cybernetix.model.Galaxy;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/v1/galaxy")
public class GalaxyResource
{
	@Autowired
	private GalaxyService galaxyService;

	//--------------------------------------------------------------------------------------------------------------------------------
	// Returns all galaxies. Sample usage: curl http://localhost:8080/Galaxy/api/v1/galaxy
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<Galaxy> loadAll()
	{
		return galaxyService.loadAll();
	}
}
