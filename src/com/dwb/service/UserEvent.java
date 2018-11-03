package com.dwb.service;

import com.dwb.myclass.Dvd;
import com.dwb.myclass.User;

public interface UserEvent {
    /**
     * 查询所有DVD信息
     * @return
     */
	Object[][] queryalldvd();
	   /**
     * 查询热门DVD信息
     * @return
     */
	Object[][] queryhotdvd();
    /**
     * 查询未借出DVD信息
     * @return
     */
	Object[][] querydvds1();
    /**
     * 查询已借出DVD信息
     * @return
     */
	Object[][] querydvds0();
	/**
	 * 查询该用户所有record记录
	 * @return
	 */
	Object[][] queryUserAllrecord(String uname);
	/**
	 *  查询该用户为归还record记录
	 * @param uname
	 * @return
	 */
	Object[][] queryUserAllrecord0(String uname);
	/**
	 *  查询该用户已归还record记录
	 * @param uname
	 * @return
	 */
	Object[][] queryUserAllrecord1(String uname);
	
	/**
	 * 执行借书操作，改变DVD状态，和records
	 * @param did(DVDid)
	 * @return
	 */
	int lenddvd(Dvd dvd,User user);
	/**
	 * 执行借书操作，改变DVD状态，和records
	 * @param id
	 * @param did
	 * @return
	 */
	int returnRecord(int id,int did);
	
	
}
