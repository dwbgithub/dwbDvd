package com.dwb.dao;

import com.dwb.myclass.User;

public interface UserDao {
	/**
	 *���ݿ�������û���ע��ʱ�õ���
	 * @param user
	 * @return dint
	 */
	public int saveUser(User user);
	/**
	 * �����û�������
	 * @param user
	 * @return dint
	 */
	public int updateUser(User user);//?
	/**
	 * ����Ա���vi�û�����չ���ܣ�
	 * @param user
	 * @return dint
	 */
	public int saveVipUser(User user);
	/**
	 * ���ݿ���ɾ���û�������
	 * @param id
	 * @return dint
	 */
	public int delUser(int id);
	/**
	 * ���ݿ��в�ѯ�û��������루��¼��֤��
	 * @param user
	 * @return User
	 */
	public User queryUser(User user);
	/**
	 * ��ѯ�û����Ƿ���ڣ�ע����֤��
	 * @param uname
	 * @return User
	 */
	public User queryUserByuname(String uname);
}
