package com.dwb.dao;

import java.util.List;

import com.dwb.myclass.Dvd;


public interface DvdDao {
	/**
	 * ���ݿⴢ��DVD����
	 * @param Dvd
	 * @return int 
	 */
	public int saveDvd(Dvd Dvd);
    /**
     * ���ݿ����DVD����
     * @param Dvd
     * @return int 
     */
	public int updateDvd(Dvd Dvd);
	/**
	 * ͨ��id����DVD
	 * @param id
	 * @return
	 */
	public int updateDvd(int id);
    /**
     * ���ݿ���ɾ��ָ��DVD
     * @param did
     * @return int 
     */
	public int delDvd(int did);
    /**
     * ���ݿ��и���ָ��DVD
     * @param did
     * @return int 
     */
	public int updateDvd(int did,int dcount,int status);
    /**
     * ���ݿ��в�ѯ����DVD
     * @return List<Dvd> 
     */
	public List<Dvd> queryDvds();
    /**
     * ���ݿ��а�DVD����ѯDVD
     * @param dname
     * @return List<Dvd> 
     */
	public Dvd queryDvdByName(String dname);

    /**
     * ���ݿ����DVD�ı������ѯDVD
     * @param did
     * @return Dvd 
     */
	public Dvd queryDvdById(int did);
    /**
     * ���ݿ����״̬��ѯDVD
     * @param status
     * @return List<Dvd> 
     */
	public List<Dvd> queryDvdByStatus(int status);
    /**
     * ���ݿ���ݽ����������
     * @return List<Dvd>
     */
	public List<Dvd> queryDvdByRanking();
}
