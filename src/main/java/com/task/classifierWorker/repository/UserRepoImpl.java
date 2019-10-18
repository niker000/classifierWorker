package com.task.classifierWorker.repository;

import com.task.classifierWorker.model.Role;
import com.task.classifierWorker.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class UserRepoImpl implements UserRepo {
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;

    public UserRepoImpl(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createUser(User user) {
        Map<String, Object> args = new HashMap<>();
        args.put("login", user.getLogin());
        args.put("password", user.getPassword());
        args.put("role_id", user.getRole().getId());

        return simpleJdbcInsert.execute(args) != 0;
    }
}
