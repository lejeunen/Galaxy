// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// --------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "tPlanet")
public class Planet extends BaseModel
{
	@Basic
	@Column(unique = true)
	private String name;

	@Basic
	@Column
	private String orbitalSpeed;

	@Basic
	@Column
	private String distanceFromSun;

	@Basic
	@Column
	private String radius;

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
	public String getOrbitalSpeed()
	{
		return orbitalSpeed;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setOrbitalSpeed(String orbitalSpeed)
	{
		this.orbitalSpeed = orbitalSpeed;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDistanceFromSun()
	{
		return distanceFromSun;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setDistanceFromSun(String distanceFromSun)
	{
		this.distanceFromSun = distanceFromSun;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getRadius()
	{
		return radius;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setRadius(String radius)
	{
		this.radius = radius;
	}
}
