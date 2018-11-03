package com.dwb.dao;

import java.util.List;

import com.dwb.myclass.Record;
import com.dwb.myclass.Recordview;

public interface RecordDao {
	/**
	 * ����Record��¼
	 * @param record
	 * @return int 
	 */
	public int saveRecord(Record record);
	/**
	 * ����Record��¼������Ա��
	 * @param record
	 * @return int 
	 */
	public int updateRecord(Record record);
	/**
	 * ɾ��records��¼
	 * @param did
	 * @return
	 */
	public int delRecord(int did);
	/**
	 * ����id���ù黹ʱ��(����Ա)
	 * @param str
	 * @param id
	 * @return
	 */
	public int renturnRecord(String returentime,int id);
	/**
	 * �鿴���н軹��¼(����Ա)
	 * @return List<VirtualRecord> 
	 */
	public List<Recordview> queryAllRecords();
	/**
	 *�鿴ָ��id(records��id)��DVD�軹��¼
	 * @param rid
	 * @return Record 
	 */
	public List<Recordview> queryRecordById(int id);
	/**
	 * ͨ��DVD����ѯ��¼(����Ա)
	 * @param dname
	 * @return
	 */
	public Recordview queryRecordBydname(String dname);
	/**
	 * ͨ���û�����ѯ��¼���û���
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname(String uname);
	/**
	 * ��ѯһ���û���δ�黹��¼
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname0(String uname);
	/**
	 * ��ѯһ���û����ѹ黹��¼
	 * @param uname
	 * @return
	 */
	public List<Recordview> queryRecordByuname1(String uname);
//	/**
//	 * ͨ���û�id��ѯ��¼
//	 * @param uids
//	 * @return
//	 */
//	public List<Record> queryRecordByUids(int uids);
	/**
	 * 
	 *�鿴id��DVD�軹��¼
	 * @param rid
	 * @return Record 
	 */
	public List<Record> queryRecordByDid(int did);
//
//	/**
//	 *���ݽ��ʱ��鿴��Ӧ�Ĺ黹��¼
//	 * @param flag
//	 * @param uname
//	 * @return List<VirtualRecord> 
//	 */
//	public List<Record> queryUserRecordsBylendTime(String lendtime);
//
//	/**
//	 *���ݹ黹ʱ��鿴��Ӧ�Ĺ黹��¼
//	 * @param flag
//	 * @param uname
//	 * @return List<VirtualRecord> 
//	 */
//	public List<Record> queryUserRecordsByReturnTime(String returntime);
}
