package com.ks.todo.main.bean.svc.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ks.todo.core.exception.SvcException;
import com.ks.todo.main.bean.TodoItem;
import com.ks.todo.main.bean.svc.TodoSvc;
import com.ks.todo.main.constant.TodoConstant;
import com.ks.todo.main.repo.TodoItemRepo;

@Service
public class TodoSvcImpl extends AbstractTodo implements TodoSvc {

	@Autowired
	protected TodoItemRepo todoItemRepo;
	
	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#findTodoItems(String)
	 */
	@Override
	public List<TodoItem> findTodoItems(String username) {
		return findTodoItems(username);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#findTodoItemsByFilterStatus(String, String...)
	 */
	@Override
	public List<TodoItem> findTodoItemsByFilterStatus(String username, String... statuses) {
		if (StringUtils.isEmpty(username)) {
			throw new SvcException("User Authorization Required");
		}

		if (!CollectionUtils.sizeIsEmpty(statuses)) {
			return todoItemRepo.findByUsernameAndItemStatusNotIn(username, Arrays.asList(statuses));
		}
		
		return todoItemRepo.findByUsername(username);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#addTodoItem(TodoItem, String)
	 */
	@Override
	public TodoItem addTodoItem(TodoItem item2Add, String username) {
		if (StringUtils.isEmpty(username)) {
			throw new SvcException("User Authorization Required");
		}

		item2Add.setItemStatus(TodoConstant.ITEM_STATUS_INCOMPLETED);
		item2Add.setUsername(username);
		return todoItemRepo.save(item2Add);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#deleteTodoItem(Long)
	 */
	@Override
	public void deleteTodoItem(Long itemId) {
		deleteTodoItem(itemId, null, false);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#deleteTodoItem(Long, String)
	 */
	@Override
	public void deleteTodoItem(Long itemId, String username) {
		deleteTodoItem(itemId, username, true);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#deleteTodoItem(Long, String, boolean)
	 */
	@Override
	public void deleteTodoItem(Long itemId, String username, boolean validateReq) {
		TodoItem todoItem = todoItemRepo.findByItemId(itemId);

		if (validateReq) {
			validateTodoItemOwner(todoItem, itemId, username);
		}

		todoItemRepo.delete(todoItem);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#markAsComplete(Long)
	 */
	@Override
	public TodoItem markAsComplete(Long itemId) {
		return markAsComplete(itemId, null, false, false);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#markAsComplete(Long, String)
	 */
	@Override
	public TodoItem markAsComplete(Long itemId, String username) {
		return markAsComplete(itemId, username, false, true);
	}

	/**
	 * @see com.ks.todo.main.bean.svc.TodoSvc#markAsComplete(Long, String, boolean, boolean)
	 */
	@Override
	public TodoItem markAsComplete(Long itemId, String username, boolean allowUnmark, boolean validateReq) {
		TodoItem todoItem = todoItemRepo.findByItemId(itemId);

		if (validateReq) {
			validateTodoItemOwner(todoItem, itemId, username);
		}

		if (allowUnmark) {
			unmarkOrMarkCompleteItem(todoItem);
		} else {
			todoItem.setItemStatus(TodoConstant.ITEM_STATUS_COMPLETED);
		}

		return todoItemRepo.save(todoItem);
	}

	/**
	 * Unmark / Mark Complete Item
	 * 
	 * @param todoItem
	 */
	private void unmarkOrMarkCompleteItem(TodoItem todoItem) {
		if (null != todoItem) {
			if (TodoConstant.ITEM_STATUS_COMPLETED.equals(todoItem.getItemStatus())) {
				todoItem.setItemStatus(TodoConstant.ITEM_STATUS_INCOMPLETED);
			} else {
				todoItem.setItemStatus(TodoConstant.ITEM_STATUS_COMPLETED);
			}
		}
	}

}
