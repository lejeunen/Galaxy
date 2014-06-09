// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service.impl;

import org.springframework.stereotype.Service;
import com.cybernetix.service.PlanetService;
import com.cybernetix.persist.PlanetDao;
import com.cybernetix.model.Planet;

//--------------------------------------------------------------------------------------------------------------------------------
@Service
public class PlanetServiceImpl extends AbstractServiceImpl<Planet, PlanetDao> implements PlanetService
{
}
