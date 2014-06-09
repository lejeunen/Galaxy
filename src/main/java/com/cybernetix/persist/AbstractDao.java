// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.persist;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

// --------------------------------------------------------------------------------------------------------------------------------
public class AbstractDao<ENTITY> extends HibernateDaoSupport
{
	private Class<ENTITY> modelClass;

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public AbstractDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
		setModelClass(getFirstTypeArgument(getClass()));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<ENTITY> loadAll()
	{
		return prepareCriteria().list();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public ENTITY loadByPrimaryKey(Long primaryKey)
	{
		return getHibernateTemplate().get(getModelClass(), primaryKey);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public ENTITY save(ENTITY entity)
    {
		getHibernateTemplate().saveOrUpdate(entity);

        return entity;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
	public void delete(ENTITY entity)
	{
		getHibernateTemplate().delete(entity);
    }

	// --------------------------------------------------------------------------------------------------------------------------------
	protected Criteria prepareCriteria()
	{
		return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(getModelClass());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected Class<ENTITY> getModelClass()
	{
		return modelClass;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected void setModelClass(Class<ENTITY> modelClass)
	{
		this.modelClass = modelClass;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes" })
	protected Class getFirstTypeArgument(Class<?> clazz)
	{
		return getTypeArgument(clazz, 0);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected Class<?> getTypeArgument(Class<?> clazz, int i)
	{
		if (clazz.getGenericSuperclass() != null && ParameterizedType.class.isAssignableFrom(clazz.getGenericSuperclass().getClass()))
		{
			Type[] actualTypeArguments = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
			if (actualTypeArguments.length>i)
				return (Class<?>) actualTypeArguments[i];
			else
				return null;
		}
		else
		{
			if (clazz.getSuperclass() != null)
				return getFirstTypeArgument(clazz.getSuperclass());
			else
				return null;
		}
	}
}
