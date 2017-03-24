package com.sj.ilaoshi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.ilaoshi.dao.UserDao;
import com.sj.ilaoshi.entity.User;
@Service
public class UserService implements UserDao {
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> findUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.findUser(map);
	}

}
