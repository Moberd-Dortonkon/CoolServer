package com.test3.test3;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VolonteerMapper implements RowMapper<Volonteer> {

	@Override
	public Volonteer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		
		Volonteer volonteer = new Volonteer(null, false, false);
		volonteer.setName(rs.getString("name"));
		volonteer.setCome(rs.getBoolean("come"));
		volonteer.setEat(rs.getBoolean("eat"));
		return volonteer;
	}
	
	

}