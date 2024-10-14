package com.example.task.Mapper;

import com.example.task.dto.TaskDTO;
import com.example.task.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskDTO taskToTaskDTO (Task task){
        if(task == null)
            return null;
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskStatus(task.getTaskStatus())
                .targetDate(task.getTargetDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .assignedUsers(task.getAssignedUsers())
                .build();
    }

    public Task taskDTOToTask (TaskDTO taskDTO){
        if(taskDTO == null)
            return null;
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setTargetDate(LocalDateTime.parse(taskDTO.getTargetDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        task.setAssignedUsers(taskDTO.getAssignedUsers());
        return task;
    }

    public List<TaskDTO> tasksToTasksDTO(List<Task> tasks) {
        List<TaskDTO> tasksDTO = new ArrayList<>();
        tasks.forEach(task -> tasksDTO.add(taskToTaskDTO(task)));
        return tasksDTO;
    }

}
