package com.ks.todo.core.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Create / Update Info
 * 
 * @author Ken Shen
 * @version 1.0
 */
@MappedSuperclass
public class CreationInfo {
	
	/**
	 * Created By
	 */
	@Column(name = "created_by", nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	protected String createdBy = "system";
	
	/**
	 * Created At
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	protected Date createdAt = new Date();
	
	/**
	 * Updated By
	 */
	@Column(name = "updated_by", nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	protected String updatedBy = "system";
	
	/**
	 * Updated At
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	protected Date updatedAt = new Date();

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
