package com.dwb.jdbcutils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PSS {
	/**
	 * һ���ص���������PreparedStatement
	 * ����Ҫִ�е�SQL����е�ռλ���滻�ɾ��������
	 * @param pstmt
	 * @throws SQLException
	 */
	public void setValues(PreparedStatement pstmt) throws SQLException;
}
