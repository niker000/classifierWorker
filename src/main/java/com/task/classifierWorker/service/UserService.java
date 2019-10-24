package com.task.classifierWorker.service;

import com.task.classifierWorker.dto.UserDTO;
import com.task.classifierWorker.exception.ElementExistException;
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
        if (userRepo.isExist(userDTO.getUsername())) {
            throw new ElementExistException("Such user is already exist");
        }
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), Role.USER, true);
        userRepo.createUser(user);
        return user;
    }
}
