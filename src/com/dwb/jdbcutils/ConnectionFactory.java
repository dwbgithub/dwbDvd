package com.dwb.jdbcutils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 连接数据库
 * @author dwb
 *
 */
public class ConnectionFactory {
	private static String DRIVERNAME = null;
	private static String URL = null;
	private static String USER = null;
	private static String PWD = null;
	private static Connection con = null;
	static{//静态代码块，类加载的时候执行，只执行1次
		Properties pt = new Properties();//Map容器
		InputStream ins = ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			pt.load(ins);//加载文件读取流
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ins != null){
				try {
					ins.close();//物理资源释放
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		DRIVERNAME = pt.getProperty("oracle.driver");
		URL = pt.getProperty("oracle.url");
		USER = pt.getProperty("oracle.user");
		PWD = pt.getProperty("oracle.pwd");
	}
	/**
	 * 获得数据库连接的封装方法
	 * @return Connection连接对象
	 */
	public static Connection getConnection(){
		try {
			Class.forName(DRIVERNAME);//1.加载驱动
			con = DriverManager.getConnection(URL, USER, PWD);//2.获得连接
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("连接获取失败");
			e.printStackTrace();
		}
		return con;
	}
}
