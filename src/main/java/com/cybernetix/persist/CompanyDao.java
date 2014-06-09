// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.persist;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cybernetix.model.Company;

//--------------------------------------------------------------------------------------------------------------------------------
@Repository
public class CompanyDao extends AbstractDao<Company>
{
	//--------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	public CompanyDao(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}
}
