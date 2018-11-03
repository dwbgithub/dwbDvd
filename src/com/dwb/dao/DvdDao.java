package com.dwb.dao;

import java.util.List;

import com.dwb.myclass.Dvd;


public interface DvdDao {
	/**
	 * 数据库储存DVD数据
	 * @param Dvd
	 * @return int 
	 */
	public int saveDvd(Dvd Dvd);
    /**
     * 数据库更新DVD数据
     * @param Dvd
     * @return int 
     */
	public int updateDvd(Dvd Dvd);
	/**
	 * 通过id跟新DVD
	 * @param id
	 * @return
	 */
	public int updateDvd(int id);
    /**
     * 数据库中删除指定DVD
     * @param did
     * @return int 
     */
	public int delDvd(int did);
    /**
     * 数据库中跟新指定DVD
     * @param did
     * @return int 
     */
	public int updateDvd(int did,int dcount,int status);
    /**
     * 数据库中查询所有DVD
     * @return List<Dvd> 
     */
	public List<Dvd> queryDvds();
    /**
     * 数据库中按DVD名查询DVD
     * @param dname
     * @return List<Dvd> 
     */
	public Dvd queryDvdByName(String dname);

    /**
     * 数据库根据DVD的编号来查询DVD
     * @param did
     * @return Dvd 
     */
	public Dvd queryDvdById(int did);
    /**
     * 数据库根据状态查询DVD
     * @param status
     * @return List<Dvd> 
     */
	public List<Dvd> queryDvdByStatus(int status);
    /**
     * 数据库根据借出次数排序
     * @return List<Dvd>
     */
	public List<Dvd> queryDvdByRanking();
}
