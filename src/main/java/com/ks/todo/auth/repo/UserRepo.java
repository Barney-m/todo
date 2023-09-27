package com.ks.todo.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ks.todo.auth.bean.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	User findByUsername(String username);
}
