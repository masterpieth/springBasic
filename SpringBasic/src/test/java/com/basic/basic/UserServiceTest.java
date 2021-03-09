package com.basic.basic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.basic.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceTest {
	
	@Autowired
	UserService userService;

	private ArrayList<HashMap<String, Object>> users = new ArrayList<HashMap<String,Object>>();
	
	@Before
	public void init() {
		HashMap<String, Object> user1 = new HashMap<String, Object>();
		HashMap<String, Object> user2 = new HashMap<String, Object>();
		
		user1.put("USERNAME", "user1");
		user1.put("PASSWORD", "1234");
		user1.put("ENABLED", 1);
		user1.put("AUTHORITY", "USER");
		
		user2.put("USERNAME", "user1");
		user2.put("PASSWORD", "1234");
		user2.put("ENABLED", 1);
		user2.put("AUTHORITY", "ADMIN");
		
		users.add(user1);
		users.add(user2);
	}
	
	@Test
	public void selectUser() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("username", "user1");
		
		HashMap<String, Object> userInfo = userService.selectUsers(params).get(0);
		
		assertThat(users.get(0).get("USERNAME"), is(userInfo.get("USERNAME")));
		assertThat(users.get(0).get("PASSWORD"), is(userInfo.get("PASSWORD")));
		assertThat(users.get(0).get("ENABLED"), is(userInfo.get("ENABLED")));
		assertThat(users.get(0).get("AUTHORITY"), is(userInfo.get("AUTHORITY")));
	}
}
