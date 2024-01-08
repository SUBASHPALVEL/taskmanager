package com.project.taskmanager.service.impl;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanager.converter.UserConverter;
import com.project.taskmanager.dto.UserDTO;
import com.project.taskmanager.entity.UserEntity;
import com.project.taskmanager.repository.UserRepository;
import com.project.taskmanager.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Mapper dozerMapper;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return userConverter.convertToDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user -> dozerMapper.map(users, UserDTO.class)).collect(Collectors.toList());

    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userConverter::convertToDTO)
                .orElse(null);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setUsermail(userDTO.getUsermail());
                    existingUser.setPassword(userDTO.getPassword());
                    UserEntity updatedUser = userRepository.save(existingUser);
                    return userConverter.convertToDTO(updatedUser);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

}