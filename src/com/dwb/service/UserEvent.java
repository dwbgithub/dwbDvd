package com.dwb.service;

import com.dwb.myclass.Dvd;
import com.dwb.myclass.User;

public interface UserEvent {
    /**
     * ��ѯ����DVD��Ϣ
     * @return
     */
	Object[][] queryalldvd();
	   /**
     * ��ѯ����DVD��Ϣ
     * @return
     */
	Object[][] queryhotdvd();
    /**
     * ��ѯδ���DVD��Ϣ
     * @return
     */
	Object[][] querydvds1();
    /**
     * ��ѯ�ѽ��DVD��Ϣ
     * @return
     */
	Object[][] querydvds0();
	/**
	 * ��ѯ���û�����record��¼
	 * @return
	 */
	Object[][] queryUserAllrecord(String uname);
	/**
	 *  ��ѯ���û�Ϊ�黹record��¼
	 * @param uname
	 * @return
	 */
	Object[][] queryUserAllrecord0(String uname);
	/**
	 *  ��ѯ���û��ѹ黹record��¼
	 * @param uname
	 * @return
	 */
	Object[][] queryUserAllrecord1(String uname);
	
	/**
	 * ִ�н���������ı�DVD״̬����records
	 * @param did(DVDid)
	 * @return
	 */
	int lenddvd(Dvd dvd,User user);
	/**
	 * ִ�н���������ı�DVD״̬����records
	 * @param id
	 * @param did
	 * @return
	 */
	int returnRecord(int id,int did);
	
	
}
