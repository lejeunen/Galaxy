// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// --------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "tGalaxy")
public class Galaxy extends BaseModel
{
	@Basic
	@Column(unique = true)
	private String name;

	@Basic
	@Column
	private String alternateName;

	@Basic
	@Column
	private String distanceFromEarth;

	@Basic
	@Column
	private String nbStars;

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setName(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getAlternateName()
	{
		return alternateName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setAlternateName(String alternateName)
	{
		this.alternateName = alternateName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDistanceFromEarth()
	{
		return distanceFromEarth;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setDistanceFromEarth(String distanceFromEarth)
	{
		this.distanceFromEarth = distanceFromEarth;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getNbStars()
	{
		return nbStars;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setNbStars(String nbStars)
	{
		this.nbStars = nbStars;
	}
}
