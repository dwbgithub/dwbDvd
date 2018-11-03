package com.dwb.jdbcutils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PSS {
	/**
	 * 一个回调函数，给PreparedStatement
	 * 的所要执行的SQL语句中的占位符替换成具体的数据
	 * @param pstmt
	 * @throws SQLException
	 */
	public void setValues(PreparedStatement pstmt) throws SQLException;
}
