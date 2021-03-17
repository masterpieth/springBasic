package com.basic.basic.dao;

import java.util.HashMap;
import java.util.List;

public interface UserDAO {
	List<HashMap<String, Object>> selectUsers(HashMap<String, Object> params);
	
	int selectUsersTotalCount(HashMap<String, Object> params);
}
