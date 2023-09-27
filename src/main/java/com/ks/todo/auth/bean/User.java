package com.ks.todo.auth.bean;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ks.todo.core.bean.BaseInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User extends BaseInfo implements Serializable, UserDetails {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2739484995592219103L;

	/**
	 * Username
	 */
	@Id
	protected String username;
	
	/**
	 * Password
	 */
	protected String password;
	
	/**
	 * Full Name
	 */
	protected String fullName;
	
	/**
	 * User Status
	 */
	protected String userStatus;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
