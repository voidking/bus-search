package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.voidking.util.ConnectMysql;
import com.voidking.model.Line;

public class LineService {
	private Connection conn = null;
	
	public LineService(){
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public ArrayList<Line> lineList(){
        ArrayList<Line> result = new ArrayList<Line>();
        try {
            String sql = "select * from bus_line where deleted = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 获取查询的结果集            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String bus_name = rs.getString("bus_name");
                String full_name = rs.getString("full_name");
                String first_stop = rs.getString("first_stop");
                String last_stop = rs.getString("last_stop");
                Line line = new Line(id, bus_name, full_name, first_stop, last_stop);
                result.add(line);
            }
            
            pstmt.close();
            
            return result;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
	
	public Line getLine(String bus_name) {
		// 结果
		Line result = null;
		try {
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select * from bus_line where bus_name=" + bus_name;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String busName = rs.getString("bus_name");
				String fullName = rs.getString("full_name");
				String firstStop = rs.getString("first_stop");
				String lastStop = rs.getString("last_stop");

				result = new Line(id, busName, fullName, firstStop, lastStop);

			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} 
		return result;
	}
	
	public ArrayList<Line> searchLine(String key) {
		// 结果集合
		ArrayList<Line> result = new ArrayList<Line>();
		try {
			String sql = "select * from bus_line where full_name like ? or first_stop like ? or last_stop like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setString(1, "%" + key + "%" ); 
			pstmt.setString(2, "%" + key + "%" ); 
			pstmt.setString(3, "%" + key + "%" );  
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String busName = rs.getString("bus_name");
				String fullName = rs.getString("full_name");
				String firstStop = rs.getString("first_stop");
				String lastStop = rs.getString("last_stop");

				Line line = new Line(id, busName, fullName, firstStop, lastStop);
				result.add(line);

			}
			rs.close();
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		return result;
	}
	
	public boolean creatLine(String busName, String fullName, String firstStop,String lastStop){
        try {
            String sql = "insert into bus_line(bus_name,full_name,first_stop,last_stop) values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, busName);
            pstmt.setString(2, fullName);
            pstmt.setString(3, firstStop);
            pstmt.setString(4, lastStop);
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            int newId = -1;
            if(rs.next()){
                newId = rs.getInt(1);
                pstmt.close();
                //Line line = this.findById(newId);
                return true;
            }else{
                pstmt.close();
            }
            
    
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
	
	public Line findById(int id) {
        try {
            String sql = "select * from bus_line where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 设定参数
            pstmt.setInt(1, id); 
            // 获取查询的结果集            
            ResultSet rs = pstmt.executeQuery();
            
            Line resultLine = null;
            if(rs.next()){
                int line_id = rs.getInt("id");
                String bus_name = rs.getString("bus_name");
                String full_name = rs.getString("full_name");
                String first_stop = rs.getString("first_stop");
                String last_stop = rs.getString("last_stop");
                
                resultLine = new Line(line_id, bus_name, full_name,first_stop,last_stop);
            }   
            return resultLine;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        return null;
    }
	
	public boolean deleteLineById(int id){
        try {
            String sql;
            sql = "update bus_line set deleted = 1 where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
}
