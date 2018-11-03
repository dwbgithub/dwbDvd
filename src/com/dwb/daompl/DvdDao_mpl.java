package com.dwb.daompl;

import java.util.List;

import com.dwb.dao.DvdDao;
import com.dwb.jdbcutils.Jdbcsu;
import com.dwb.jdbcutils.PSS;
import com.dwb.jdbcutils.RSCallBack;
import com.dwb.myclass.Dvd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DvdDao_mpl implements DvdDao{

	@Override
	public int saveDvd(final Dvd Dvd) {
		int result=0;
		String sql="insert into Dvds(id,dname,dcount,status)values(seq_dvds.nextval,?,?,?)";
		Jdbcsu jdbcsu=new Jdbcsu();
		result=jdbcsu.update(sql, new PSS() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, Dvd.getDname());
				pstmt.setInt(2, Dvd.getDcount());
				pstmt.setInt(3, Dvd.getStatus());
			}
		});
		return result;
	}

	@Override
	public int updateDvd(final Dvd Dvd) {
		int result=0;
		String sql="update Dvds set dname=?,dcount=?,status=? where id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		result=jdbcsu.update(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, Dvd.getDname());
				pstmt.setInt(2, Dvd.getDcount());
				pstmt.setInt(3, Dvd.getStatus());
				pstmt.setInt(4, Dvd.getId());
			}
		});
		return result;
	}

	@Override
	public int delDvd(final int did) {
		int result=0;
		String sql="delete from Dvds where id=?";
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
	public List<Dvd> queryDvds() {
		final List<Dvd> dList=new ArrayList<>();
		String sql="select*from dvds";
		Jdbcsu jdbcsu=new Jdbcsu();
		jdbcsu.query(sql, null,new RSCallBack() {
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Dvd dvd = new Dvd();
					dvd.setId(rs.getInt("id"));
					dvd.setDname(rs.getString("dname"));
					dvd.setDcount(rs.getInt("dcount"));
					dvd.setStatus(rs.getInt("status"));
					dList.add(dvd);
			         }
		          }
		});
		return dList;
	}



	@Override
	public Dvd queryDvdById(final int id) {
		final Dvd dvd=new Dvd();
		String sql="select*from dvds where id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1,id );
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while(rs.next()) {
					dvd.setId(rs.getInt("id"));
					dvd.setDname(rs.getString("dname"));
					dvd.setDcount(rs.getInt("dcount"));
					dvd.setStatus(rs.getInt("status"));
				}
			}
		});
		return dvd;
	}

	@Override
	public List<Dvd> queryDvdByStatus(final int status) {
		final List<Dvd> qDvdsbys=new ArrayList<>();
		String sql="select*from dvds where status=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1,status );
			}
		},new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while(rs.next()) {
					Dvd dvd = new Dvd();
					dvd.setId(rs.getInt("id"));
					dvd.setDname(rs.getString("dname"));
					dvd.setDcount(rs.getInt("dcount"));
					dvd.setStatus(rs.getInt("status"));
					qDvdsbys.add(dvd);
					
				}
			}
		});
		return qDvdsbys;
	}

	@Override
	public Dvd queryDvdByName(final String dname) {
		final Dvd dvd=new Dvd();
		String sql="select*from dvds where dname=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		jdbcsu.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, dname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				if (rs.next()) {
					dvd.setId(rs.getInt("id"));
					dvd.setDname(rs.getString("dname"));
					dvd.setDcount(rs.getInt("dcount"));
					dvd.setStatus(rs.getInt("status"));
				}
			}
		});
		
		return dvd;
	}

	@Override
	public List<Dvd> queryDvdByRanking() {
		final List<Dvd> qDvdsbyd=new ArrayList<>();
		String sql="select*from dvds where dcount>5 order by dcount desc";
		Jdbcsu jdbcsu=new Jdbcsu();
		jdbcsu.query(sql, null,new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				while(rs.next()) {
					Dvd dvd = new Dvd();
					dvd.setId(rs.getInt("id"));
					dvd.setDname(rs.getString("dname"));
					dvd.setDcount(rs.getInt("dcount"));
					dvd.setStatus(rs.getInt("status"));
					qDvdsbyd.add(dvd);
					
				}
			}
		});
		return qDvdsbyd;
	}

	@Override
	public int updateDvd(final int did,final int dcount,final int status) {
		String sql="update Dvds set dcount=?,status=? where id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		return jdbcsu.update(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, dcount);
				pstmt.setInt(2, Math.abs(status-1));
				pstmt.setInt(3, did);
			}
		});
	      
	}

	@Override
	public int updateDvd(final int id) {
		int result=0;
		String sql="update Dvds set dcount=?,status=? where id=?";
		Jdbcsu jdbcsu=new Jdbcsu();
		result=jdbcsu.update(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setInt(1, );
//				pstmt.setInt(2, );
//				pstmt.setInt(3, id);
			}
		});
		return result;
	}
}
