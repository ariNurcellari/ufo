package it.polito.tdp.ufo.dp;
import java.util.*;
import java.sql.*;

public class testDB {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=Gestionale1992" ;
		try {
			Connection conn = DriverManager.getConnection(jdbcURL) ;
			
		//	Statement st =conn.createStatement();
			// Query sena parametri!!
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
			System.out.println(formeUFO);
			
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
			
			System.out.println("UFO di forma "+shapeScelta+" sono: "+count);
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
