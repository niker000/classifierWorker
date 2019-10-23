package com.task.classifierWorker.mapper;

import com.task.classifierWorker.model.Role;
import com.task.classifierWorker.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setActive(resultSet.getBoolean("active"));
        user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));

        return user;
    }
}
