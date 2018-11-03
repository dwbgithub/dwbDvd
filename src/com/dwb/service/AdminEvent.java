package com.dwb.service;

import com.dwb.myclass.Dvd;

public interface AdminEvent {
	/**
	 * 添加DVD
	 * @return
	 */
	int savedvd(Dvd dvd);
	/**
	 * 更新DVD
	 * @param dvd
	 * @return
	 */
	int upatedvd(Dvd dvd);
	/**
	 * 删除DVD
	 * @return
	 */
	int deldvd(Dvd dvd);
    /**
     * 通过DVD名查询记录
     * @param dname
     * @return
     */
	Object[][] querydvdbydname(String dname);

    /**
     * 通过ID查询记录
     * @param id
     * @return
     */
	Object[][] querydvdbyid(int id);
	/**
	 * 查询所有record
	 * @return
	 */
    Object[][] queryrecord();
    /**
     * 通过DVD名查询record记录
     * @param dname
     * @return
     */
	Object[][] queryrecordbydname(String dname);
	/**
	 * 通过DVDID查询record记录
	 * @param id
	 * @return
	 */
	Object[][] queryrecordbydid(int id);
	
}
