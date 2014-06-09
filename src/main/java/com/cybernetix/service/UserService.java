// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service;

import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
public interface UserService extends AbstractService<User>
{
	public User loadUserByUsername(String loginId);

	public User getSelf();
}
