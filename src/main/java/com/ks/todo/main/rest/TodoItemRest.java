package com.ks.todo.main.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.todo.core.annotation.SecuredRoute;

import jakarta.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/todo")
public class TodoItemRest {

	@SecuredRoute
	@GetMapping
	public String test() {
		return "test";
	}
}
