package com.project.taskmanager.service;

import java.util.List;

import com.project.taskmanager.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    boolean deleteUser(Long userId);
}
