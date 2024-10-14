package com.example.task.controller;

import com.example.task.dto.TaskDTO;
import com.example.task.dto.UserDTO;
import com.example.task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        if (userService.deleteUser(userId)) {
            return ResponseEntity.ok(String.format("User with id %d deleted", userId));
        } else {
            return ResponseEntity.badRequest().body("Wrong userId");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUser(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        return ResponseEntity.ok(userService.findByNameOrSurname(name, surname));
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getById(@RequestParam int taskId){
        return ResponseEntity.ok(userService.getUserById(taskId));
    }
}
