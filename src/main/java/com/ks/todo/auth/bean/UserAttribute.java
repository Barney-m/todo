package com.ks.todo.auth.bean;

import java.io.Serializable;

import com.ks.todo.core.bean.BaseInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAttribute extends BaseInfo implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7563156186255643572L;

	/**
	 * Username / Email
	 */
	@Id
	protected String username;
	
	/**
	 * Provider
	 */
	protected String provider;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
}
