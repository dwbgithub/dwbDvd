package com.dwb.jdbcutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RSCallBack {
	/**
	 * 处理结果集的一个回调函数
	 * @param rs结果集
	 * @throws SQLException
	 */
	   public void processRs(ResultSet rs)throws SQLException;
}
