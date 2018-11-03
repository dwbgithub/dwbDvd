package com.dwb.jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据库查询增删改工具
 * @author dwb
 *
 */
public class Jdbcsu {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
     * 关闭操作
     * @param rs结果集（数据库）
     * @param pstmt预执行（数据库）
     * @param conn连接（数据库）
     */
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 增删改操作
	 * @param sql数据库命令
	 * @param setter占位符数据
	 * @return int判断是否成功
	 */
	public int update(String sql, PSS setter) {
		int res = 0;
		try {
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (setter != null) {
				setter.setValues(pstmt);
			}
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return res;
	}

	/**
	 *  执行查询语句
	 * @param sql数据库命令
	 * @param setter占位符数据
	 * @param handler处理结果集的操作
	 */
	public void query(String sql, PSS setter, RSCallBack handler) {
		try {
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (setter != null) {
				setter.setValues(pstmt);
			}
			rs = pstmt.executeQuery();
			if (handler != null) {
				handler.processRs(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
	}
}
