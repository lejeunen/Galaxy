// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service.impl;

import org.springframework.stereotype.Service;
import com.cybernetix.service.GalaxyService;
import com.cybernetix.persist.GalaxyDao;
import com.cybernetix.model.Galaxy;

//--------------------------------------------------------------------------------------------------------------------------------
@Service
public class GalaxyServiceImpl extends AbstractServiceImpl<Galaxy, GalaxyDao> implements GalaxyService
{
}
