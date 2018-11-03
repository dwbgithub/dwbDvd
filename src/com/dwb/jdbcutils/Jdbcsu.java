package com.dwb.jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ���ݿ��ѯ��ɾ�Ĺ���
 * @author dwb
 *
 */
public class Jdbcsu {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
     * �رղ���
     * @param rs����������ݿ⣩
     * @param pstmtԤִ�У����ݿ⣩
     * @param conn���ӣ����ݿ⣩
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
	 * ��ɾ�Ĳ���
	 * @param sql���ݿ�����
	 * @param setterռλ������
	 * @return int�ж��Ƿ�ɹ�
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
	 *  ִ�в�ѯ���
	 * @param sql���ݿ�����
	 * @param setterռλ������
	 * @param handler���������Ĳ���
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
