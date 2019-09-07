package lf.blank.dao;

import lf.blank.entity.User;

/**
 * UserDao类 用假数据做测试用
 * 
 * @author blank
 */
public class UserDao {
	public User queryUser(User user) {
		if (user.getName().equals("blank") && user.getPassword().equals("blank"))
			return user;
		else
			return null;
	}
}
