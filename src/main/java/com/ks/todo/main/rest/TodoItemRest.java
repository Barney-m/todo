package com.ks.todo.main.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.todo.core.svc.HttpSecuritySvc;
import com.ks.todo.main.bean.TodoItem;
import com.ks.todo.main.bean.svc.TodoSvc;
import com.ks.todo.main.repo.TodoItemRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/todo")
@Tag(name = "TODO Item", description = "TODO Item APIs")
public class TodoItemRest {

	@Autowired
	protected TodoItemRepo todoItemRepo;

	@Autowired
	protected HttpSecuritySvc httpSecuritySvc;

	@Autowired
	protected TodoSvc todoSvc;

	/**
	 * Find TODO Item
	 * 
	 * @return List<TodoItem>
	 */
	@Operation(summary = "Find TODO Items", description = "Find TODO Items by Username")
	@GetMapping
	public List<TodoItem> findTodoItem() {
		// To get username from context holder
		return todoSvc.findTodoItemsByFilterStatus(httpSecuritySvc.getUsername());
	}

	/**
	 * Add TODO Item
	 * 
	 * @param todoItem
	 * @return TodoItem
	 */
	@Operation(summary = "Add TODO Item", description = "Add TODO Item")
	@PostMapping
	public TodoItem addTodoItem(@RequestBody TodoItem todoItem) {
		return todoSvc.addTodoItem(todoItem, httpSecuritySvc.getUsername());
	}

	/**
	 * Delete TODO Item
	 * 
	 * @param itemId
	 */
	@Operation(summary = "Delete TODO Item", description = "Delete TODO Item")
	@PostMapping("/{itemId}/delete")
	public void deleteTodoItem(@PathVariable Long itemId) {
		todoSvc.deleteTodoItem(itemId, httpSecuritySvc.getUsername());
	}

	/**
	 * Mark TODO Item as Completed
	 * 
	 * @param itemId
	 * @return TodoItem
	 */
	@Operation(summary = "Mark TODO Item As Completed", description = "Mark TODO item as completed")
	@PostMapping("/{itemId}/markAsComplete")
	public TodoItem markAsComplete(@PathVariable Long itemId) {
		return todoSvc.markAsComplete(itemId, httpSecuritySvc.getUsername());
	}
}
