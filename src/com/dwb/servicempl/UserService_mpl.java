package com.dwb.servicempl;

import com.dwb.daompl.UserDao_mpl;
import com.dwb.myclass.User;
import com.dwb.service.IUserService;

public class UserService_mpl  implements IUserService{
	@Override
	public int registerUser(User user) {
		int res = 0;
		User u =new UserDao_mpl().queryUserByuname(user.getUname());
		if (u.getUname() != null) {
			return -1;
		} else {
			res =new UserDao_mpl().saveUser(user);
		}
		return res;
	}

	@Override
	public User login(User user) {
		return new UserDao_mpl().queryUser(user);
	}

}
