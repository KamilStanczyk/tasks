package com.example.task.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
}
