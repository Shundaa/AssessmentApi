package com.wipro.api.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import oracle.jdbc.datasource.OracleDataSource;

@Service
public class OracleConnectionService {
	
	public void getConnetcion() throws SQLException {
		OracleDataSource ods = new oracle.jdbc.pool.OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//10.197.83.50:1521/XE"); // jdbc:oracle:thin@//[nome do host]:[porta]/[nome do serviço de BD]
		ods.setUser("system"); // [nome do usuário]
		ods.setPassword("wiprodb"); // [senha]
		Connection conn = ods.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT 'Hello World!' FROM dual");
		ResultSet rslt = stmt.executeQuery();
		while (rslt.next()) {
			System.out.println(rslt.getString(1));
		}
	}
}
