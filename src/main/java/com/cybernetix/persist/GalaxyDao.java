// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.persist;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cybernetix.model.Galaxy;

//--------------------------------------------------------------------------------------------------------------------------------
@Repository
public class GalaxyDao extends AbstractDao<Galaxy>
{
	//--------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	public GalaxyDao(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}
}
