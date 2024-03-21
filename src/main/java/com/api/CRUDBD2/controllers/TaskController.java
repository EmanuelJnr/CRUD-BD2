package com.api.CRUDBD2.controllers;

import com.api.CRUDBD2.dtos.TaskDTO;
import com.api.CRUDBD2.models.TaskModel;
import com.api.CRUDBD2.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/task")
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskDTO taskDTO) {
        if (taskService.existsByTitle(taskDTO.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This title is already in use!");
        }
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }

    @GetMapping
    public ResponseEntity<Page<TaskModel>> getAllTasks(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id")UUID id) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
        }
        taskService.delete(taskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id") UUID id, @RequestBody @Valid TaskDTO taskDTO) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
        }
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        taskModel.setId(taskModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskModel));
    }
}