package com.test3.test3;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapCoordiantesMapper implements RowMapper<MapCoordinates> {

	@Override
	public MapCoordinates mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return new MapCoordinates(rs.getString("latlng"),
				rs.getString("name"),
				rs.getString("type"));
	}

}
