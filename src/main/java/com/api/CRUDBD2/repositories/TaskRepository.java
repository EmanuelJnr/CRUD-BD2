package com.api.CRUDBD2.repositories;

import com.api.CRUDBD2.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository <TaskModel, UUID> {
    boolean existsByTitle(String title);
}