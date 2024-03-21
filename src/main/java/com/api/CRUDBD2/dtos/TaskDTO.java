package com.api.CRUDBD2.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TaskDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime dueDate;
    @NotBlank
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}