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

import jakarta.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/todo")
public class TodoItemRest {

	@Autowired
	protected TodoItemRepo todoItemRepo;

	@Autowired
	protected HttpSecuritySvc httpSecuritySvc;

	@Autowired
	protected TodoSvc todoSvc;

	@GetMapping
	public List<TodoItem> findTodoItem() {
		// To get username from context holder
		return todoItemRepo.findByUsername(httpSecuritySvc.getUsername());
	}

	@PostMapping
	public TodoItem addTodoItem(@RequestBody TodoItem todoItem) {
		return todoSvc.addTodoItem(todoItem, httpSecuritySvc.getUsername());
	}

	@PostMapping("/{itemId}/delete")
	public void deleteTodoItem(@PathVariable Long itemId) {
		todoSvc.deleteTodoItem(itemId, httpSecuritySvc.getUsername());
	}

	@PostMapping("/{itemId}/markAsComplete")
	public TodoItem markAsComplete(@PathVariable Long itemId) {
		return todoSvc.markAsComplete(itemId, httpSecuritySvc.getUsername());
	}
}
