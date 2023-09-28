package com.ks.todo.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ks.todo.main.bean.TodoItem;

@Repository
public interface TodoItemRepo extends JpaRepository<TodoItem, Long> {

	/**
	 * Find all by username
	 * 
	 * @param username
	 * @return List<TodoItem>
	 */
	List<TodoItem> findByUsername(String username);
	
	/**
	 * Find by item id
	 * 
	 * @param itemId
	 * @return TodoItem
	 */
	TodoItem findByItemId(Long itemId);
}
