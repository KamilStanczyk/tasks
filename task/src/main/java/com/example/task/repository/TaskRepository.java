package com.example.task.repository;

import com.example.task.model.Task;
import com.example.task.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByTargetDateBetweenAndTaskStatus(LocalDateTime startDate, LocalDateTime endDate, TaskStatus status);
    List<Task> findByTaskStatus(TaskStatus status);
    List<Task> findByTargetDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
