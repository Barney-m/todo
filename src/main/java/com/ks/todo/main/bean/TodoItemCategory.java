package com.ks.todo.main.bean;

import java.io.Serializable;

import com.ks.todo.core.bean.BaseInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TodoItemCategory extends BaseInfo implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5617170730891619817L;

	/**
	 * Username / Email
	 */
	@Id
	protected String username;
	
	/**
	 * Item Category Name
	 */
	protected String itemCategoryName;
	
	/**
	 * Item Category Status
	 */
	protected String itemCategoryStatus;
	
}
