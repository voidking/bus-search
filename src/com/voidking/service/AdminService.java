package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.voidking.model.Admin;
import com.voidking.util.ConnectMysql;

public class AdminService {
	
	private Connection conn = null;
	
	public AdminService() {
		// TODO Auto-generated constructor stub
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public Admin login(String username, String password){
		try {
			String sql;
			sql = "select * from bus_admin where username=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setString(1, username); 
			pstmt.setString(2, password);
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			
			Admin admin = new Admin();
			if(rs.first()){
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				pstmt.close();
				
				return admin;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public boolean updatePwd(int id, String newPwd){
		try {
			String sql;
			sql = "update bus_admin set password=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, id);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		AdminService service = new AdminService();
		Admin admin = service.login("haojin", "haojin");
		if(admin != null){
			System.out.println(admin.getId());
		}else{
			System.out.println("用户名或密码错误");
		}

		
	}
}
