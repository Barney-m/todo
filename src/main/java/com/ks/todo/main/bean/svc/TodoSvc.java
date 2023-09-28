package com.ks.todo.main.bean.svc;

import com.ks.todo.main.bean.TodoItem;

public interface TodoSvc {
	
	/**
	 * Add TODO Item
	 * 
	 * @param item2Add
	 * @param username
	 * @return TodoItem - Updated Object
	 */
	TodoItem addTodoItem(TodoItem item2Add, String username);

	/**
	 * Delete TODO Item without validate username
	 * 
	 * @param itemId
	 */
	void deleteTodoItem(Long itemId);
	
	/**
	 * Delete TODO Item
	 * 
	 * @param itemId
	 * @param username
	 */
	void deleteTodoItem(Long itemId, String username);
	
	/**
	 * Delete TODO Item with Optional username Validation
	 * 
	 * @param itemId
	 * @param username
	 * @param validateReq
	 */
	void deleteTodoItem(Long itemId, String username, boolean validateReq);
	
	/**
	 * Mark TODO Item as Complete without validate username
	 * 
	 * @param itemId
	 * @return TodoItem
	 */
	TodoItem markAsComplete(Long itemId);
	
	/**
	 * Mark TODO Item as Complete
	 * 
	 * @param itemId
	 * @param username
	 * @return TodoItem
	 */
	TodoItem markAsComplete(Long itemId, String username);
	
	/**
	 * Mark TODO Item as Complete with Optional username validation
	 * 
	 * @param itemId
	 * @param username
	 * @return TodoItem
	 */
	TodoItem markAsComplete(Long itemId, String username, boolean allowUnmark, boolean validateReq);
}
