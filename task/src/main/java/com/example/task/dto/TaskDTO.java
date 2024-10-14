package com.example.task.dto;

import com.example.task.model.TaskStatus;
import com.example.task.model.User;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TaskDTO {
    private int id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private String targetDate;
    private List<User> assignedUsers;
}
