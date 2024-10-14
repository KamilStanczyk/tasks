package com.example.task.service;

import com.example.task.Mapper.UserMapper;
import com.example.task.dto.UserDTO;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public UserDTO getUserById(int userId){
        return userMapper.userToUserDto(userRepository.findById(userId).orElse(null));
    }

    public UserDTO addUser(UserDTO user) {
        return userMapper.userToUserDto(userRepository.save(userMapper.userDTOToUser(user)));
    }

    public boolean deleteUser(int userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    public List<UserDTO> findByNameOrSurname(String name, String surname) {
        return userMapper.usersToUsersDto(userRepository.findByNameOrSurname(name, surname));
    }

}
