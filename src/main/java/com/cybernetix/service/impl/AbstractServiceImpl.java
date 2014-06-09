// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybernetix.service.AbstractService;
import com.cybernetix.persist.AbstractDao;

//--------------------------------------------------------------------------------------------------------------------------------
@Service
public abstract class AbstractServiceImpl<ENTITY, DAO extends AbstractDao<ENTITY>> implements AbstractService<ENTITY>
{
	@Autowired
	private AbstractDao<ENTITY> dao;

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public List<ENTITY> loadAll()
	{
		return dao.loadAll();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public ENTITY loadByPrimaryKey(Long primaryKey)
	{
		return dao.loadByPrimaryKey(primaryKey);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public ENTITY loadByPrimaryKey(String primaryKey)
	{
		return loadByPrimaryKey(Long.valueOf(primaryKey));
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public ENTITY save(ENTITY entity)
	{
		return dao.save(entity);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(ENTITY entity)
	{
		dao.delete(entity);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(Long primaryKey)
	{
		ENTITY entity = dao.loadByPrimaryKey(primaryKey);
		if (entity != null)
			delete(entity);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(String primaryKey)
	{
		delete(Long.valueOf(primaryKey));
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public AbstractDao<ENTITY> getDao()
	{
		return dao;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setDao(AbstractDao<ENTITY> dao)
	{
		this.dao = dao;
	}
}
