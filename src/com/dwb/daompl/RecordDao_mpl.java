package com.dwb.daompl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dwb.dao.RecordDao;
import com.dwb.jdbcutils.Jdbcsu;
import com.dwb.jdbcutils.PSS;
import com.dwb.jdbcutils.RSCallBack;
import com.dwb.myclass.Record;
import com.dwb.myclass.Recordview;

public class RecordDao_mpl implements RecordDao{

	@Override
	public int saveRecord(final Record record) {
		String sql="insert into records values(seq_records.nextval,?,?,?,?)";
		Jdbcsu jdbcsu=new Jdbcsu();
		return jdbcsu.update(sql,new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, record.getUids());
				pstmt.setInt(2, record.getDid());
				pstmt.setString(3, record.getLendTime());
				pstmt.setString(4, record.getReturnTime());
			}
		} );
	}

	@Override
	public int updateRecord(final Record record) {
		String sql= "update records set uids=?,did=?,lendTime=?,returnTime=? where id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		return jdbcsu.update(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, record.getUids());
				pstmt.setInt(2, record.getDid());
				pstmt.setString(3, record.getLendTime());
				pstmt.setString(4, record.getReturnTime());
				pstmt.setInt(5,record.getId());
			}
		});
	}

	@Override
	public List<Recordview> queryAllRecords() {
		final List<Recordview> list=new ArrayList<>();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime from records r,users u,dvds d where u.id=r.uids and r.did=d.id";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, null, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Recordview recordview=new Recordview();
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
					list.add(recordview);
				}
			}
		});
	     return list;
	}

	@Override
	public List<Recordview> queryRecordById(final int id) {
		final List<Recordview> list=new ArrayList<>();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime from records r,"
				+" users u,dvds d where u.id=r.uids and r.did=d.id and r.id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Recordview recordview=new Recordview();
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
					list.add(recordview);
				}
			}
		});
	     return list;
	}



	@Override
	public List<Recordview> queryRecordByuname(final String uname) {
		final List<Recordview> list=new ArrayList<>();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime"+
				" from records r,users u,dvds d where u.id=r.uids and r.did=d.id and u.uname=?";
		
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, uname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Recordview recordview=new Recordview();
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
					list.add(recordview);
				}
			}
		});
	     return list;
	}

	@Override
	public List<Recordview> queryRecordByuname0(final String uname) {
		final List<Recordview> list=new ArrayList<>();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime"+
				" from records r,users u,dvds d where u.id=r.uids and r.did=d.id and u.uname=? and returnTime ='δ�黹'";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, uname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Recordview recordview=new Recordview();
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
					list.add(recordview);
				}
			}
		});
	     return list;
	}

	@Override
	public List<Recordview> queryRecordByuname1(final String uname) {
		final List<Recordview> list=new ArrayList<>();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime"+
				" from records r,users u,dvds d where u.id=r.uids and r.did=d.id and u.uname=?  and returnTime  !='δ�黹' ";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, uname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Recordview recordview=new Recordview();
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
					list.add(recordview);
				}
			}
		});
	     return list;
	}

	@Override
	public int renturnRecord(final String returentime,final int id) {
		String sql="update records set returnTime=? where id=? ";
		Jdbcsu jdbcsu=new Jdbcsu();
		return jdbcsu.update(sql,new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,returentime);
				pstmt.setInt(2,id);
			}
		} );
	}

	@Override
	public int delRecord(final int did) {
		int result=0;
		String sql="delete from records where did=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		result=jdbcsu.update(sql, new PSS() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				 pstmt.setInt(1, did);
			}
		});
		return result;
	}

	@Override
	public Recordview queryRecordBydname(final String dname) {
		final Recordview recordview=new Recordview();
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime from records r,"
				+" users u,dvds d where u.id=r.uids and r.did=d.id and d.dname=?";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, dname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					recordview.setId(rs.getInt(1));
					recordview.setDid(rs.getInt(2));
					recordview.setUname(rs.getString(3));
					recordview.setDname(rs.getString(4));
					recordview.setLendTime(rs.getString(5));
					recordview.setReturnTime(rs.getString(6));
				}
			}
		});
	     return recordview;
	}
//	@Override
//	public List<Record> queryRecordByUids(final int uids) {
//		final List<Record> list=new ArrayList<>();
//		String sql="select*from records where uids=?";
//		Jdbcsu jdbcsu=new Jdbcsu();
//	     jdbcsu.query(sql, new PSS() {
//			
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setInt(1, uids);
//			}
//		}, new RSCallBack() {
//			
//			@Override
//			public void processRs(ResultSet rs) throws SQLException {
//				while (rs.next()) {
//					Record record=new Record();
//					record.setId(rs.getInt(1));
//					record.setUids(rs.getInt(2));
//					record.setDid(rs.getInt(3));
//					record.setLendTime(rs.getString(4));
//					record.setReturnTime(rs.getString(5));
//					list.add(record);
//				}
//			}
//		});
//	     return list;
//	}
//
	@Override
	public List<Record> queryRecordByDid(final int did) {
		final List<Record> list=new ArrayList<>();
		String sql="select*from records where did=?";
		Jdbcsu jdbcsu=new Jdbcsu();
	     jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, did);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Record record=new Record();
					record.setId(rs.getInt(1));
					record.setUids(rs.getInt(2));
					record.setDid(rs.getInt(3));
					record.setLendTime(rs.getString(4));
					record.setReturnTime(rs.getString(5));
					list.add(record);
				}
			}
		});
	     return list;
	}

//	@Override
//	public List<Record> queryUserRecordsBylendTime(final String lendtime) {
//		final List<Record> list=new ArrayList<>();
//		String sql="select*from records where lendtime=?";
//		Jdbcsu jdbcsu=new Jdbcsu();
//	     jdbcsu.query(sql, new PSS() {
//			
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, lendtime);
//			}
//		}, new RSCallBack() {
//			
//			@Override
//			public void processRs(ResultSet rs) throws SQLException {
//				while (rs.next()) {
//					Record record=new Record();
//					record.setId(rs.getInt(1));
//					record.setUids(rs.getInt(2));
//					record.setDid(rs.getInt(3));
//					record.setLendTime(rs.getString(4));
//					record.setReturnTime(rs.getString(5));
//					list.add(record);
//				}
//			}
//		});
//	     return list;
//	}
//
//	@Override
//	public List<Record> queryUserRecordsByReturnTime(final String returntime) {
//		final List<Record> list=new ArrayList<>();
//		String sql="select*from records where returntime=?";
//		Jdbcsu jdbcsu=new Jdbcsu();
//	     jdbcsu.query(sql, new PSS() {
//			
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, returntime);
//			}
//		}, new RSCallBack() {
//			
//			@Override
//			public void processRs(ResultSet rs) throws SQLException {
//				while (rs.next()) {
//					Record record=new Record();
//					record.setId(rs.getInt(1));
//					record.setUids(rs.getInt(2));
//					record.setDid(rs.getInt(3));
//					record.setLendTime(rs.getString(4));
//					record.setReturnTime(rs.getString(5));
//					list.add(record);
//				}
//			}
//		});
//	     return list;
//	}


}
