package com.ks.todo.main.bean.svc.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ks.todo.core.exception.SvcException;
import com.ks.todo.main.bean.TodoItem;

public abstract class AbstractTodo {

	public void validateTodoItemOwner(List<TodoItem> items, final Long itemId, final String username) {
		boolean found = false;

		if (CollectionUtils.isNotEmpty(items)) {
			for (TodoItem item : items) {
				if (item.getItemId() == itemId && item.getUsername().equals(username)) {
					found = true;
					break;
				}
			}
		} else {
			throw new SvcException("TODO Item not found");
		}

		if (!found) {
			throw new SvcException("TODO Item not belongs to " + username);
		}
	}

	public void validateTodoItemOwner(TodoItem item, final Long itemId, final String username) {
		if (null == item) {
			throw new SvcException("TODO Item not found");
		}

		if (null == itemId || null == username || item.getItemId() != itemId || !item.getUsername().equals(username)) {
			throw new SvcException("TODO Item not belongs to " + username);
		}
	}

}
