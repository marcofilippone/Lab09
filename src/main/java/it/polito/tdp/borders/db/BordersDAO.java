package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("ccode"))) {
					Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
					idMap.put(rs.getInt("ccode"), c);
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public List<Country> getVertici(Map<Integer, Country> idMap, int year){
		String sql = "SELECT DISTINCT cu.CCode, cu.StateAbb, cu.StateNme "
				+ "FROM contiguity cy, country cu "
				+ "WHERE cy.state1no = cu.CCode AND cy.year<=? AND cy.conttype=1";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(idMap.get(rs.getInt("ccode")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	

	public List<Border> getCountryPairs(Map<Integer, Country> idMap, int anno) {

		String sql = "SELECT cy.state1no, cy.state2no "
				+ "FROM contiguity cy, country cu "
				+ "WHERE cy.state1no = cu.CCode AND cy.year<=? AND cy.conttype=1";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Border(idMap.get(rs.getInt("cy.state1no")), idMap.get(rs.getInt("cy.state2no"))));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
