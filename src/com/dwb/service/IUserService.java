package com.dwb.service;

import com.dwb.myclass.User;
public interface IUserService {
           /**
            * ע���û�
            * @param user
            * @return
            */
		   public int registerUser(User user);
		   /**
		    * �û���¼
		    * @param user
		    * @return
		    */
		   public User login(User user);
		   }
