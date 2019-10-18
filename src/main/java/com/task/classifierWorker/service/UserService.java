package com.task.classifierWorker.service;

import com.task.classifierWorker.dto.UserDTO;
import com.task.classifierWorker.model.Role;
import com.task.classifierWorker.model.User;
import com.task.classifierWorker.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword(), Role.USER);
        userRepo.createUser(user);
        return user;
    }
}
