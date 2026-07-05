package com.javacart.service;

import java.util.List;

import com.javacart.dao.UserDAO;
import com.javacart.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.saveUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public boolean updateProfile(User user) {
        return userDAO.updateProfile(user);
    }

    public boolean changePassword(int userId,
                                  String currentPassword,
                                  String newPassword) {
        return userDAO.changePassword(userId, currentPassword, newPassword);
    }

}