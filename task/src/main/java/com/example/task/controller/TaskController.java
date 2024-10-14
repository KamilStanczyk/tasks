package com.example.task.controller;

import com.example.task.dto.TaskDTO;
import com.example.task.model.Task;
import com.example.task.model.TaskStatus;
import com.example.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.addTask(task));
    }

    @PostMapping("/edit")
    public ResponseEntity<TaskDTO> editTask(@RequestParam int taskId, @RequestBody TaskDTO task) {
        task = taskService.editTask(taskId, task);
        if (task!=null){
            return ResponseEntity.ok(task);
        } else{
            return ResponseEntity.badRequest().body(task);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam int taskId) {
        if (taskService.deleteTask(taskId)) {
            return ResponseEntity.ok(String.format("Task with id %d deleted", taskId));
        } else {
            return ResponseEntity.badRequest().body("Wrong taskId");
        }
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam int taskId, @RequestParam TaskStatus newStatus) {
        if (taskService.changeStatus(taskId, newStatus)) {
            return ResponseEntity.ok(String.format("Task %d status change to %s", taskId, newStatus));
        } else {
            return ResponseEntity.badRequest().body("Wrong taskId");
        }
    }

    @PostMapping("/assignUser")
    public ResponseEntity<String> assignUserToTask(@RequestParam int userId, int taskId) {
        if (taskService.assignUserToTask(taskId, userId)) {
            return ResponseEntity.ok(String.format("User %d assign to task %d", taskId, userId));
        } else {
            return ResponseEntity.badRequest().body("Wrong ids");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> searchTask(@RequestParam(required = false) String fromDate ,@RequestParam(required = false) String toDate, @RequestParam(required = false) TaskStatus taskStatus){
        return ResponseEntity.ok(taskService.searchTask(fromDate,toDate,taskStatus));
    }

    @GetMapping("/get")
    public ResponseEntity<TaskDTO> getById(@RequestParam int taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }
}
