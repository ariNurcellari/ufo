package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
		
	public static Connection getConnection() throws SQLException{
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=Gestionale1992" ;
		return DriverManager.getConnection(jdbcURL) ;
	}
}
