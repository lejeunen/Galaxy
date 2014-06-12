// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Service;
import com.cybernetix.service.UserService;
import com.cybernetix.persist.UserDao;
import com.cybernetix.model.Role;
import com.cybernetix.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
@Service("securityUserService")
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao> implements UserService, UserDetailsService, ConsumerDetailsService
{
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Value("${appDirectOAuthConsumerKey}")
	private String appDirectOAuthConsumerKey;

	@Value("${appDirectOAuthConsumerSecret}")
	private String appDirectOAuthConsumerSecret;

	//--------------------------------------------------------------------------------------------------------------------------------
	// loginId could actually be a username, an email address or and openId token
	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public User loadUserByUsername(String loginId)
	{
		User user = userDao.loadByLoginId(loginId);

		if (user == null)
			throw new UsernameNotFoundException("No matching user found.");

		return user;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public User getSelf()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null)
			return null;

		return (User) authentication.getPrincipal();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String oauthConsumerKey) throws OAuthException
	{
		// for now Galaxy app only supports oauth requests from AppDirect. For that reason we lookup for that fixed consumer key only.

		log.debug(String.format("appDirect consumer key: %s", appDirectOAuthConsumerKey));
		log.debug(String.format("request consumer key: %s", oauthConsumerKey));

		if (!oauthConsumerKey.equals(appDirectOAuthConsumerKey))
			throw new OAuthException(String.format("Consumer key '%s' not recognized.", oauthConsumerKey));

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.OAUTH_APPDIRECT.toString()));

		SharedConsumerSecret signatureSecret = new SharedConsumerSecretImpl(appDirectOAuthConsumerSecret);		

		BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
		consumerDetails.setAuthorities(authorities);
		consumerDetails.setConsumerName("AppDirect - Galaxy API consumer");
		consumerDetails.setConsumerKey(appDirectOAuthConsumerKey);
		consumerDetails.setSignatureSecret(signatureSecret);
		consumerDetails.setRequiredToObtainAuthenticatedToken(false);

		return consumerDetails;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public UserDao getUserDao()
	{
		return userDao;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
}
