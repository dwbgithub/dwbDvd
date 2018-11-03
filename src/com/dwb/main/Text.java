
package com.dwb.main;

import com.dwb.myclass.User;
import com.dwb.view.AdminDvdView;
import com.dwb.view.AdminMainView;
import com.dwb.view.AdminRecordView;
import com.dwb.view.LoginView;
import com.dwb.view.UserMainView;
import com.dwb.view.UserRecordView;
import com.dwb.view.UserRegisterView;
import com.dwb.view.UserquerylendView;

public class Text {
	
		public static void main(String[] args) {
			new LoginView();//优化界面
			User user=new User();
//			user.setId(25);
//			user.setUname("wang");
//			user.setType(1);
//			user.setUpass("123456");
//			user.setUname("fff");
			
			new AdminMainView();//界面优化 
	        new AdminDvdView();
			new AdminRecordView();
			
			new UserRegisterView();
			new UserMainView(user);//界面优化 
			new UserquerylendView(user);
			new UserRecordView(user);
		}
	}

