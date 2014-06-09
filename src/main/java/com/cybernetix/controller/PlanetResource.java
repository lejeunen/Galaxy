// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cybernetix.service.PlanetService;
import com.cybernetix.model.Planet;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/v1/planet")
public class PlanetResource
{
	@Autowired
	private PlanetService planetService;

	//--------------------------------------------------------------------------------------------------------------------------------
	// Returns all planets. Sample usage: curl http://localhost:8080/Galaxy/api/v1/planet
	//--------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<Planet> loadAll()
	{
		return planetService.loadAll();
	}
}
