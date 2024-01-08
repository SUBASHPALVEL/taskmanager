package com.project.taskmanager.service;


import com.project.taskmanager.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long taskId);

    TaskDTO createTask(TaskDTO newTask);

    TaskDTO updateTask(Long taskId, TaskDTO updatedTask);

    void deleteTask(Long taskId);
}
