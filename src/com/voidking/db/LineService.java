package com.voidking.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.voidking.model.Line;

public class LineService {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/bus";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public ArrayList<Line> getLines() {
		// 结果集合
		ArrayList<Line> result = new ArrayList<Line>();

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select * from line";
			ResultSet rs = stmt.executeQuery(sql);

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

				// Display values
				// System.out.print("ID: " + id);
				// System.out.print(", busName: " + busName);
				// System.out.println(", fullName: " + fullName);

			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return result;
	}

	public Line getLine(String bus_name) {
		// 结果
		Line result = null;

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "select * from line where bus_name=" + bus_name;
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
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return result;
	}

	public ArrayList<Line> searchLine(String key) {
		// 结果集合
		ArrayList<Line> result = new ArrayList<Line>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql;
			sql = "select * from line where full_name like ?";
			pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setString(1, "%" + key + "%" );        
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
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
			} 
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		} 
		return result;
	}

//	public static void main(String[] args) {
//		LineService lineService = new LineService();
//		lineService.searchLine("16");
//	}
}
