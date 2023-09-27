package com.ks.todo.main.bean;

import java.io.Serializable;

import com.ks.todo.core.bean.BaseInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TodoItem extends BaseInfo implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 236096940618472272L;

	/**
	 * Item ID
	 */
	@Id
	protected Long itemId;
	
	/**
	 * Username / Email
	 */
	protected String username;
	
	/**
	 * Item Category ID
	 */
	protected Long itemCategoryId;
	
	/**
	 * Item Title
	 */
	protected String itemTitle;
	
	/**
	 * Item Description
	 */
	protected String itemDesc;
	
	/**
	 * Item Status
	 */
	protected String itemStatus;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
}
