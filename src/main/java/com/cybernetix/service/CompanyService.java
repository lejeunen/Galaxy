// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service;

import com.cybernetix.model.Company;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
public interface CompanyService extends AbstractService<Company>
{
	public void save(Company company, User user);
}
