package com.example.task.service;

import com.example.task.Mapper.TaskMapper;
import com.example.task.dto.TaskDTO;
import com.example.task.model.Task;
import com.example.task.model.TaskStatus;
import com.example.task.model.User;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;
    TaskMapper taskMapper;

    public List<TaskDTO> getAllTasks() {
        return taskMapper.tasksToTasksDTO(taskRepository.findAll());
    }

    public TaskDTO getTaskById(int taskId){
        return taskMapper.taskToTaskDTO(taskRepository.findById(taskId).orElse(null));
    }

    public TaskDTO addTask(TaskDTO task) {
        return taskMapper.taskToTaskDTO(taskRepository.save(taskMapper.taskDTOToTask(task)));
    }

    public boolean deleteTask(int taskId) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }

    public boolean changeStatus(int taskId, TaskStatus newStatus) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setTaskStatus(newStatus);
                    taskRepository.save(task);
                    return true;
                })
                .orElse(false);
    }

    public boolean assignUserToTask(int taskId, int userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent() && user.isPresent()) {
            task.get().getAssignedUsers().add(user.get());
            taskRepository.save(task.get());
            return true;
        } else return false;
    }


    public TaskDTO editTask(int taskId, TaskDTO task) {
        return taskRepository.findById(taskId)
                .map(tmpTask -> {
                    editTaskWithTask(tmpTask, taskMapper.taskDTOToTask(task));
                    return taskMapper.taskToTaskDTO(taskRepository.save(tmpTask));
                })
                .orElse(null);
    }

    private void editTaskWithTask(Task oldTask, Task editedTask) {
        if (editedTask.getTitle() != null) {
            oldTask.setTitle(editedTask.getTitle());
        }
        if (editedTask.getDescription() != null) {
            oldTask.setDescription(editedTask.getDescription());
        }
        if (editedTask.getTargetDate() != null) {
            oldTask.setTargetDate(editedTask.getTargetDate());
        }
        if (editedTask.getTaskStatus() != null) {
            oldTask.setTaskStatus(editedTask.getTaskStatus());
        }
    }

    public List<TaskDTO> searchTask(String fromDate, String toDate, TaskStatus taskStatus) {
        if (fromDate == null || toDate == null) {
            if (taskStatus == null) {
                return null;
            } else {
                return taskMapper.tasksToTasksDTO(taskRepository.findByTaskStatus(taskStatus));
            }
        } else {
            if (taskStatus == null) {
                return taskMapper.tasksToTasksDTO(taskRepository.findByTargetDateBetween(dateParse(fromDate), dateParse(toDate)));
            }
            return taskMapper.tasksToTasksDTO(taskRepository.findByTargetDateBetweenAndTaskStatus(dateParse(fromDate), dateParse(toDate), taskStatus));
        }

    }

    private LocalDateTime dateParse(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
