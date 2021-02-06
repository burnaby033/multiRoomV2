package com.dms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class userDao {
	Connection conn;
	
	 public Connection getConnection() throws SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			/*본인의 db 정보를 적는 곳*/
		    conn = DriverManager.getConnection(url, "kakaoTalk_simple", "1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/*
	public void select() {
		Connection conn;
		try {
			conn = getConnection();
			String sql = "select count(id) COUNT from board";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);	
			int count;
			
			if(rs.next())
				count = rs.getInt("COUNT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */
}
