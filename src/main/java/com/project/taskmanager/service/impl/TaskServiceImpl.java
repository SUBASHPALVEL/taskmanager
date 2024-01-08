package com.project.taskmanager.service.impl;


import com.github.dozermapper.core.Mapper;
import com.project.taskmanager.converter.TaskConverter;
import com.project.taskmanager.dto.TaskDTO;
import com.project.taskmanager.entity.TaskEntity;
import com.project.taskmanager.repository.TaskRepository;
import com.project.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskConverter taskConverter;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> dozerMapper.map(tasks, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElse(null);
        return (task != null) ? taskConverter.convertToDTO(task) : null;
    }

    @Override
    public TaskDTO createTask(TaskDTO newTask) {
        TaskEntity task = taskConverter.convertToEntity(newTask);
        TaskEntity savedTask = taskRepository.save(task);
        return taskConverter.convertToDTO(savedTask);
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTask) {
        TaskEntity existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask != null) {
            taskConverter.convertToEntity(updatedTask);
            TaskEntity updatedTaskEntity = taskRepository.save(existingTask);
            return taskConverter.convertToDTO(updatedTaskEntity);
        } else {
            return null; 
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}