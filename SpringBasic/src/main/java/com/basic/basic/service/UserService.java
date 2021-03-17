package com.basic.basic.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.basic.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	
	public List<HashMap<String, Object>> selectUsers(HashMap<String, Object> params) {
		return userDAO.selectUsers(params);
	}
	
	public int selectUsersTotalCount(HashMap<String, Object> params) {
		return userDAO.selectUsersTotalCount(params);
	}
}
