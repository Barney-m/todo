package com.ks.todo.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ks.todo.auth.bean.UserAttribute;

@Repository
public interface UserAttributeRepo extends JpaRepository<UserAttribute, String> {

	UserAttribute findByUsername(String username);
}
