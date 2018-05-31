package com.test3.test3;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupMapper implements RowMapper<Group> {

	@Override
	public Group mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub	
		return new Group(rs.getString("grouptype"),rs.getString("leadername"),rs.getString("groupid"),rs.getString("groupname"));
	}

}
