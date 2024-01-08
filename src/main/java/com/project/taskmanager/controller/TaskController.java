package com.project.taskmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.taskmanager.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final List<TaskDTO> tasks = new ArrayList<>();

    // http://localhost:8080/api/tasks
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // http://localhost:8080/api/tasks/1
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        TaskDTO task = findTaskById(taskId);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/tasks
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO newTask) {
        tasks.add(newTask);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/tasks/1
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO updatedTask) {
        TaskDTO existingTask = findTaskById(taskId);
        if (existingTask != null) {
            // Update fields as needed
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setCreatedDate(updatedTask.getCreatedDate());
            existingTask.setCompletedDate(updatedTask.getCompletedDate());

            return new ResponseEntity<>(existingTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/tasks/1
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        TaskDTO task = findTaskById(taskId);
        if (task != null) {
            tasks.remove(task);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private TaskDTO findTaskById(Long taskId) {
        return tasks.stream()
                .filter(task -> task.getTaskId().equals(taskId))
                .findFirst()
                .orElse(null);
    }
}