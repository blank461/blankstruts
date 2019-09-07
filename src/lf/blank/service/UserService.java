package lf.blank.service;

import lf.blank.dao.UserDao;
import lf.blank.entity.User;

/**
 * 用户服务层
 * 
 * @author blank
 */
public class UserService {
	UserDao userDao = new UserDao();

	public User login(String name, String password) {
		return userDao.queryUser(new User(name, password));
	}
}
