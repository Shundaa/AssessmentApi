package com.wipro.api.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import oracle.jdbc.datasource.OracleDataSource;

@Service
public class OracleConnectionService {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUserName;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	public String getConnetcion(Map<String, String> model) throws SQLException {
		OracleDataSource ods = new oracle.jdbc.pool.OracleDataSource();
		StringBuilder result=new StringBuilder();
		ods.setURL(dbUrl);
		ods.setUser(dbUserName);
		ods.setPassword(dbPassword);
		Connection conn = ods.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT 'UP and Running DATA BASE' FROM dual");
		ResultSet rslt = stmt.executeQuery();
		while (rslt.next()) {
			result.append(rslt.getString(1));
		}
		System.out.println(model.toString());
			return result.toString();
	}
}
