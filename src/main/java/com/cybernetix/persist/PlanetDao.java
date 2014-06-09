// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.persist;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cybernetix.model.Planet;

//--------------------------------------------------------------------------------------------------------------------------------
@Repository
public class PlanetDao extends AbstractDao<Planet>
{
	//--------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	public PlanetDao(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}
}
