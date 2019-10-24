package com.task.classifierWorker.repository;

import com.task.classifierWorker.dto.UserDTO;
import com.task.classifierWorker.model.PurchaseEntry;
import com.task.classifierWorker.model.User;

import java.util.List;

public interface UserRepo {
    boolean createUser(User user);

    User getUserFromDb(String username);

    boolean isExist(String username);
}
