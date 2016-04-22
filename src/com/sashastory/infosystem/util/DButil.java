package com.sashastory.infosystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DButil {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pass = "oraclehr";
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Locale.setDefault(Locale.ENGLISH);
		connection = DriverManager.getConnection(jdbcUrl,user,pass);
		} catch (Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
