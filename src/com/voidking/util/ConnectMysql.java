package com.voidking.util;

import java.sql.*;

public class ConnectMysql {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// 添加 useUnicode=true&characterEncoding=utf8 解决中文存储变？的问题
	private String DB_URL = "jdbc:mysql://localhost/bus?useUnicode=true&characterEncoding=utf8";

	private String USER = "root";
	private String PASS = "mysql";
	
	private Connection conn = null;
	
	public ConnectMysql(){
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public Connection getConnection() {
		return conn;
	}// end main
}
