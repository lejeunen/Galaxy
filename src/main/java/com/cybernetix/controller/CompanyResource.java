// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import com.cybernetix.service.CompanyService;
import com.cybernetix.model.Company;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/v1/company")
public class CompanyResource
{
	@Autowired
	private CompanyService companyService;

	//--------------------------------------------------------------------------------------------------------------------------------
	// Returns all companies. Sample usage: curl http://localhost:8080/Galaxy/api/v1/company. Requires Admin role.
	//--------------------------------------------------------------------------------------------------------------------------------
	@PreAuthorize("hasRole('ROLE_ADMIN_GALAXY')")
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<Company> loadAll()
	{
		return companyService.loadAll();
	}
}
