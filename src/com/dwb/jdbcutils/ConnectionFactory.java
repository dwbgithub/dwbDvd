package com.dwb.jdbcutils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * �������ݿ�
 * @author dwb
 *
 */
public class ConnectionFactory {
	private static String DRIVERNAME = null;
	private static String URL = null;
	private static String USER = null;
	private static String PWD = null;
	private static Connection con = null;
	static{//��̬����飬����ص�ʱ��ִ�У�ִֻ��1��
		Properties pt = new Properties();//Map����
		InputStream ins = ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			pt.load(ins);//�����ļ���ȡ��
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ins != null){
				try {
					ins.close();//������Դ�ͷ�
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
	 * ������ݿ����ӵķ�װ����
	 * @return Connection���Ӷ���
	 */
	public static Connection getConnection(){
		try {
			Class.forName(DRIVERNAME);//1.��������
			con = DriverManager.getConnection(URL, USER, PWD);//2.�������
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���ӻ�ȡʧ��");
			e.printStackTrace();
		}
		return con;
	}
}
