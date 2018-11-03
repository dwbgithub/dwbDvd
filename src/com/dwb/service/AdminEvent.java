package com.dwb.service;

import com.dwb.myclass.Dvd;

public interface AdminEvent {
	/**
	 * ���DVD
	 * @return
	 */
	int savedvd(Dvd dvd);
	/**
	 * ����DVD
	 * @param dvd
	 * @return
	 */
	int upatedvd(Dvd dvd);
	/**
	 * ɾ��DVD
	 * @return
	 */
	int deldvd(Dvd dvd);
    /**
     * ͨ��DVD����ѯ��¼
     * @param dname
     * @return
     */
	Object[][] querydvdbydname(String dname);

    /**
     * ͨ��ID��ѯ��¼
     * @param id
     * @return
     */
	Object[][] querydvdbyid(int id);
	/**
	 * ��ѯ����record
	 * @return
	 */
    Object[][] queryrecord();
    /**
     * ͨ��DVD����ѯrecord��¼
     * @param dname
     * @return
     */
	Object[][] queryrecordbydname(String dname);
	/**
	 * ͨ��DVDID��ѯrecord��¼
	 * @param id
	 * @return
	 */
	Object[][] queryrecordbydid(int id);
	
}
