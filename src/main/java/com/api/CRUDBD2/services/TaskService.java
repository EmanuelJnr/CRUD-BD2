package com.api.CRUDBD2.services;

import com.api.CRUDBD2.models.TaskModel;
import com.api.CRUDBD2.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public boolean existsByTitle(String title) {
        return taskRepository.existsByTitle(title);
    }

    public Page<TaskModel> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Optional<TaskModel> findById(UUID id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void delete(TaskModel taskModel) {
        taskRepository.delete(taskModel);
    }
}