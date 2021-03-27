package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SightingDAO {
	
	public List<String> readShapes() {
		Connection conn;
		try {
			conn = dbConnect.getConnection();
		
		//	Statement st =conn.createStatement();
			// Query senza parametri!!
			String sql = "SELECT DISTINCT shape "
					+ "from sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC" ;
			
			PreparedStatement st = conn.prepareStatement(sql) ;
		
			ResultSet res = st.executeQuery();
			List<String> formeUFO = new LinkedList<String>()	;		
			while(res.next()) {
				String forma = res.getString("shape") ;
				formeUFO.add(forma) ;
			}
			st.close();
			return formeUFO ;
		}
		catch (SQLException e) {
			throw new RuntimeException("Database error in readdhape",e) ;
		}
	}
	
	public int CountByShapes(String shape) {
		Connection conn;
		try {
			conn = dbConnect.getConnection();
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? " ;
			String shapeScelta = "circle" ;
			
			PreparedStatement st2 = conn.prepareStatement(sql2) ;
			// Vado a settare il paramentro(?) nella query
			st2.setString(1, shapeScelta);
			ResultSet res2 = st2.executeQuery() ;
			// essendo che ho una sola query è sprecato utilizzare un while
			res2.first() ;
			int count = res2.getInt("cnt") ;
			st2.close();
			conn.close();
			return count ;
			
		} catch (Exception e) {
			throw new RuntimeException("Database error in countByShape",e) ;
		}
	}

}
