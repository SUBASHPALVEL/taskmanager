package com.project.taskmanager.converter;

import org.springframework.stereotype.Component;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanager.dto.UserDTO;
import com.project.taskmanager.entity.UserEntity;

@Component
public class UserConverter {

    private final Mapper mapper;

    public UserConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO convertToDTO(UserEntity userEntity) {
        return mapper.map(userEntity, UserDTO.class);
    }

    public UserEntity convertToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, UserEntity.class);
    }
    
}

