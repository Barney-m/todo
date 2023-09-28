package com.ks.todo.main;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.ks.todo.auth.bean.User;
import com.ks.todo.auth.repo.UserRepo;
import com.ks.todo.core.ClassObjFactory;
import com.ks.todo.core.exception.SvcException;
import com.ks.todo.main.bean.TodoItem;
import com.ks.todo.main.bean.svc.TodoSvc;
import com.ks.todo.main.bean.svc.impl.TodoSvcImpl;
import com.ks.todo.main.constant.TodoConstant;
import com.ks.todo.main.repo.TodoItemRepo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class TodoTests {

	private static final List<User> userList = new ArrayList<>();

	@Mock
	private TodoItemRepo todoItemRepo;

	@Mock
	private UserRepo userRepo;
	
	@Mock
	private TodoSvc todoSvc;
	
	@InjectMocks
	private TodoSvcImpl todoSvcImpl;

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(todoItemRepo);
		Assertions.assertNotNull(userRepo);
		Assertions.assertNotNull(todoSvc);
	}
	
	@Test
	public void test_addTodoItem_withEmptyUsername() {
		TodoItem todoItem = ClassObjFactory.newInstance(TodoItem.class);

		SvcException ex = new SvcException("User Authorization Required");
		
		when(todoSvc.addTodoItem(todoItem, null)).thenThrow(ex);
		when(todoSvc.addTodoItem(todoItem, StringUtils.EMPTY)).thenThrow(ex);

		Assertions.assertThrows(SvcException.class, () -> todoSvc.addTodoItem(todoItem, null));
		Assertions.assertThrows(SvcException.class, () -> todoSvc.addTodoItem(todoItem, StringUtils.EMPTY));
		SvcException threwEx3 = Assertions.assertThrows(SvcException.class, () -> todoSvcImpl.addTodoItem(todoItem, null));
		Assertions.assertEquals(ex.getMsg(), threwEx3.getMsg());
	}
	
	@Test
	public void test_addTodoItem_addItem() {
		TodoItem todoItem = ClassObjFactory.newInstance(TodoItem.class);
		todoItem.setItemTitle("Test Title 1");
		
		TodoItem savedTodoItem = ClassObjFactory.newInstance(TodoItem.class);
		savedTodoItem.setItemTitle("Test Title 1");
		savedTodoItem.setUsername("Test_User1");
		savedTodoItem.setItemId(1L);
		savedTodoItem.setItemStatus(TodoConstant.ITEM_STATUS_INCOMPLETED);
		
		when(todoSvc.addTodoItem(todoItem, "Test_User1")).thenReturn(savedTodoItem);
		Assertions.assertNotNull(todoSvc.addTodoItem(todoItem, "Test_User1"));
		
		when(todoItemRepo.save(todoItem)).thenReturn(savedTodoItem);
		Assertions.assertNotNull(todoItemRepo.save(todoItem));
		
		Assertions.assertNotNull(todoSvcImpl.addTodoItem(todoItem, "Test_User1"));
	}

	@BeforeEach
	private void setup() {
		if (CollectionUtils.isNotEmpty(userList)) {
			userRepo.saveAll(userList);
		}
	}

	// Instance Initializer
	static {
		User user1 = ClassObjFactory.newInstance(User.class);
		user1.setUsername("Test_User1");
		user1.setFullName("Test User 1");

		User user2 = ClassObjFactory.newInstance(User.class);
		user1.setUsername("Test_User2");
		user1.setFullName("Test User 2");

		User user3 = ClassObjFactory.newInstance(User.class);
		user1.setUsername("Test_User3");
		user1.setFullName("Test User 3");

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
	}
}
