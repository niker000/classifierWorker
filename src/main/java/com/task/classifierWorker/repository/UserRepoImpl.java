package com.task.classifierWorker.repository;

import com.task.classifierWorker.mapper.UserMapper;
import com.task.classifierWorker.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepoImpl implements UserRepo {
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private UserMapper userMapper;

    public UserRepoImpl(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public boolean createUser(User user) {
        Map<String, Object> args = new HashMap<>();
        args.put("username", user.getUsername());
        args.put("password", user.getPassword());
        args.put("active", user.isActive());
        args.put("role_id", user.getRole().getId());

        return simpleJdbcInsert.execute(args) != 0;
    }

    @Override
    public User getUserFromDb(String username) {
        String sql = "SELECT u.username, u.password, u.active, r.role \n" +
                "FROM users u  \n" +
                "LEFT JOIN roles r ON u.role_id = r.id \n" +
                "WHERE u.username='" + username + "'";
        List<User> users = jdbcTemplate.query(sql, userMapper);
        if(users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public boolean isExist(String username) {
        String sql = "SELECT * FROM users WHERE username='"+username+"'";
        return !jdbcTemplate.query(sql, userMapper).isEmpty();
    }
}
