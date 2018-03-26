package com.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.batch.partition.RangePartitioner;

public class UserRowMapper implements RowMapper<User> {

	private static final Logger LOGGER = Logger.getLogger(UserRowMapper.class.getName());
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();

		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("user_login"));
		user.setPassword(rs.getString("user_pass"));
		user.setAge(rs.getInt("age"));

		//LOGGER.info("Log4j: User : " + user.getId());
		return user;
	}

}