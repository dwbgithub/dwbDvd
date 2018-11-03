package com.dwb.dao;

import java.util.List;

import com.dwb.myclass.Record;
import com.dwb.myclass.Recordview;

public interface RecordDao {
	/**
	 * 保存Record记录
	 * @param record
	 * @return int 
	 */
	public int saveRecord(Record record);
	/**
	 * 更新Record记录（管理员）
	 * @param record
	 * @return int 
	 */
	public int updateRecord(Record record);
	/**
	 * 删除records记录
	 * @param did
	 * @return
	 */
	public int delRecord(int did);
	/**
	 * 根据id设置归还时间(管理员)
	 * @param str
	 * @param id
	 * @return
	 */
	public int renturnRecord(String returentime,int id);
	/**
	 * 查看所有借还记录(管理员)
	 * @return List<VirtualRecord> 
	 */
	public List<Recordview> queryAllRecords();
	/**
	 *查看指定id(records的id)的DVD借还记录
	 * @param rid
	 * @return Record 
	 */
	public List<Recordview> queryRecordById(int id);
	/**
	 * 通过DVD名查询记录(管理员)
	 * @param dname
	 * @return
	 */
	public Recordview queryRecordBydname(String dname);
	/**
	 * 通过用户名查询记录（用户）
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname(String uname);
	/**
	 * 查询一个用户的未归还记录
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname0(String uname);
	/**
	 * 查询一个用户的已归还记录
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname1(String uname);
//	/**
//	 * 通过用户id查询记录
//	 * @param uids
//	 * @return
//	 */
//	public List<Record> queryRecordByUids(int uids);
	/**
	 * 
	 *查看id的DVD借还记录
	 * @param rid
	 * @return Record 
	 */
	public List<Record> queryRecordByDid(int did);
//
//	/**
//	 *根据借出时间查看相应的归还记录
//	 * @param flag
//	 * @param uname
//	 * @return List<VirtualRecord> 
//	 */
//	public List<Record> queryUserRecordsBylendTime(String lendtime);
//
//	/**
//	 *根据归还时间查看相应的归还记录
//	 * @param flag
//	 * @param uname
//	 * @return List<VirtualRecord> 
//	 */
//	public List<Record> queryUserRecordsByReturnTime(String returntime);
}
