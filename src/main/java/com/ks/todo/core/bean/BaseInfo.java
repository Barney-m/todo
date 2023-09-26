package com.ks.todo.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Base Info
 * 
 * @author Ken Shen
 * @version 1.0
 */
@MappedSuperclass
public class BaseInfo extends CreationInfo {
	/**
	 * Modified Count
	 */
	@Column(name = "modified", nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	protected Long modified = Long.valueOf(0);

	/**
	 * @return the modified
	 */
	public Long getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Long modified) {
		this.modified = modified;
	}
}
