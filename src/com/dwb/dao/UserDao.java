package com.dwb.dao;

import com.dwb.myclass.User;

public interface UserDao {
	/**
	 *数据库中添加用户（注册时用到）
	 * @param user
	 * @return dint
	 */
	public int saveUser(User user);
	/**
	 * 更新用户（？）
	 * @param user
	 * @return dint
	 */
	public int updateUser(User user);//?
	/**
	 * 管理员添加vi用户（扩展功能）
	 * @param user
	 * @return dint
	 */
	public int saveVipUser(User user);
	/**
	 * 数据库中删除用户（？）
	 * @param id
	 * @return dint
	 */
	public int delUser(int id);
	/**
	 * 数据库中查询用户名及密码（登录验证）
	 * @param user
	 * @return User
	 */
	public User queryUser(User user);
	/**
	 * 查询用户名是否存在（注册验证）
	 * @param uname
	 * @return User
	 */
	public User queryUserByuname(String uname);
}
