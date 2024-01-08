package com.project.taskmanager.converter;

import org.springframework.stereotype.Component;

import com.github.dozermapper.core.Mapper;
import com.project.taskmanager.dto.TaskDTO;
import com.project.taskmanager.entity.TaskEntity;

@Component
public class TaskConverter {

    private final Mapper mapper;

    public TaskConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    public TaskDTO convertToDTO(TaskEntity taskEntity) {
        return mapper.map(taskEntity, TaskDTO.class);
    }

    public TaskEntity convertToEntity(TaskDTO taskDTO) {
        return mapper.map(taskDTO, TaskEntity.class);
    }
}


