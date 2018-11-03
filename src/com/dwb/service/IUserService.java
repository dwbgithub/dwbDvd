package com.dwb.service;

import com.dwb.myclass.User;
public interface IUserService {
           /**
            * 注册用户
            * @param user
            * @return
            */
		   public int registerUser(User user);
		   /**
		    * 用户登录
		    * @param user
		    * @return
		    */
		   public User login(User user);
		   }
