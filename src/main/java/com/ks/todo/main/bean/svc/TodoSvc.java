package com.ks.todo.main.bean.svc;

import java.util.List;

import com.ks.todo.main.bean.TodoItem;

public interface TodoSvc {
	
	/**
	 * Find TODO Items
	 * 
	 * @param username
	 * @return List<TodoItem>
	 */
	List<TodoItem> findTodoItems(String username);
	
	/**
	 * Find TODO Items by Filtering Status
	 * 
	 * @param username
	 * @param statuses
	 * @return List<TodoItem>
	 */
	List<TodoItem> findTodoItemsByFilterStatus(String username, String ...statuses);
	
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
