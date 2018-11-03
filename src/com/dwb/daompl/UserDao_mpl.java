package com.dwb.daompl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwb.dao.UserDao;
import com.dwb.jdbcutils.Jdbcsu;
import com.dwb.jdbcutils.PSS;
import com.dwb.jdbcutils.RSCallBack;
import com.dwb.myclass.User;

public class UserDao_mpl extends Jdbcsu implements UserDao{

	@Override
	public int saveUser(final User user) {
		String sql = "insert into users(uname,upass,type)values(?,?,?)";
		int res = this.update(sql, new PSS() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUname());
				pstmt.setString(2, user.getUpass());
				pstmt.setInt(3, user.getType());
			}
		});
		return res;
	}

	@Override
	public User queryUser(final User user) {
		final User u=new User();
		String sql = "select*from users where uname=? and upass=? and type=?";
		this.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUname());
				pstmt.setString(2, user.getUpass());
				pstmt.setInt(3, user.getType());
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				if(rs.next()){
					u.setId(rs.getInt("id"));
					u.setUname(rs.getString("uname"));
					u.setUpass(rs.getString("upass"));
					u.setType(rs.getInt("type"));
				}
				
			}
		});
		return u;
	}
	
	
	@Override
	public int delUser(final int id) {
		String sql = "delete from users where id=?";
		int res = this.update(sql, new PSS() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
			}
		});
		return res;
	}

	@Override
	public int updateUser(final User user) {
		String sql = "update users set uname=?,upass=?,type=? where id=?";
		int res = this.update(sql, new PSS() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUname());
				pstmt.setString(2, user.getUpass());
				pstmt.setInt(3, user.getType());
				pstmt.setInt(4, user.getId());
			}
		});
		return res;	
		}



	@Override
	public User queryUserByuname(final String uname) {
		final User u=new User();
		String sql = "select id,uname,upass,type from users where uname=?";
		this.query(sql, new PSS() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, uname);
			}
		}, new RSCallBack() {
			
			@Override
			public void processRs(ResultSet rs) throws SQLException {
				if(rs.next()){
					u.setId(rs.getInt("id"));
					u.setUname(rs.getString("uname"));
					u.setUpass(rs.getString("upass"));
					u.setType(rs.getInt("type"));
				}
				
			}
		});
		return u;
	}

	@Override
	public int saveVipUser(User user) {
		//type��������һ��3.����Ա����Ȩ��
		// TODO Auto-generated method stub
		return 0;
	}

}
