// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cybernetix.service.CompanyService;
import com.cybernetix.persist.CompanyDao;
import com.cybernetix.persist.UserDao;
import com.cybernetix.model.Company;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Service
public class CompanyServiceImpl extends AbstractServiceImpl<Company, CompanyDao> implements CompanyService
{
	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private UserDao userDao;

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void save(Company company, User user)
	{
		company = companyDao.save(company);
		user.setCompany(company);

		userDao.save(user);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public CompanyDao getCompanyDao()
	{
		return companyDao;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setCompanyDao(CompanyDao companyDao)
	{
		this.companyDao = companyDao;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public UserDao getUserDao()
	{
		return userDao;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
}
