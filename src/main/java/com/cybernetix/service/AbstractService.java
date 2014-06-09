// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service;

import java.util.List;

//--------------------------------------------------------------------------------------------------------------------------------
public interface AbstractService<ENTITY>
{
	public List<ENTITY> loadAll();

	public ENTITY loadByPrimaryKey(Long primaryKey);
	public ENTITY loadByPrimaryKey(String primaryKey);

	public ENTITY save(ENTITY entity);

	public void delete(ENTITY entity);
	public void delete(Long primaryKey);
	public void delete(String primaryKey);
}
