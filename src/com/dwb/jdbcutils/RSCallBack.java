package com.dwb.jdbcutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RSCallBack {
	/**
	 * ����������һ���ص�����
	 * @param rs�����
	 * @throws SQLException
	 */
	   public void processRs(ResultSet rs)throws SQLException;
}
