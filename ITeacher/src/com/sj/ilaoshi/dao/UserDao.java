package com.sj.ilaoshi.dao;



import java.util.List;
import java.util.Map;
import com.sj.ilaoshi.entity.User;


public interface UserDao {
	public List<User> findUser(Map<String, Object> map);
	
	
	
	
	
}