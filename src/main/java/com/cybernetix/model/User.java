// Copyright (c) Cybernetix Inc - 2013

package com.cybernetix.model;

import java.util.HashSet;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

// --------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "tUser")
public class User extends BaseModel implements UserDetails 
{
	private static final long serialVersionUID = 8266438151666871413L;

	@Basic
	@Column
	private String firstName;

	@Basic
	@Column
	private String lastName;

	@Basic
	@Column(unique = true)
	private String username;

	@Basic
	@Column(unique = true)
	private String openId;

	@Basic
	@Column(unique = true)
	private String email;

	@Basic
	@Column
	private Boolean isAdmin;

	@ManyToOne
	@JoinColumn(name="company")
	private Company company;

	@Basic
	@Column
	@JsonIgnore
	private String password;

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities()
	{
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));

		if (getIsAdmin() != null && getIsAdmin().booleanValue())
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));

		return authorities;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired()
	{
		return true;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked()
	{
		return true;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	@JsonIgnore
	public boolean isEnabled()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getFirstName()
	{
		return firstName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getLastName()
	{
		return lastName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getUsernameOrOpenId()
	{
		String username = getUsername();
		if (username != null)
			return username;

		if (getOpenId() != null)
			return "(openid)";

		return null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getUsername()
	{
		return username;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setUsername(String username)
	{
		this.username = username;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getOpenId()
	{
		return openId;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getEmail()
	{
		return email;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setEmail(String email)
	{
		this.email = email;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getIsAdmin()
	{
		return isAdmin;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setIsAdmin(Boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Company getCompany()
	{
		return company;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setCompany(Company company)
	{
		this.company = company;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getPassword()
	{
		return password;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPassword(String password)
	{
		this.password = password;
	}
}
