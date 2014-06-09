// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.persist;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Repository
public class UserDao extends AbstractDao<User>
{
	//--------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	public UserDao(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public User loadByLoginId(String loginId)
	{
		Criteria criteria = prepareCriteria();
		criteria.add(Restrictions.or(
			Restrictions.eq("username", loginId),
			Restrictions.eq("email", loginId),
			Restrictions.eq("openId", loginId)));

		return (User) criteria.uniqueResult();
	}
}
