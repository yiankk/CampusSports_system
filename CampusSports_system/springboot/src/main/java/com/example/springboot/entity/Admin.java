package com.example.springboot.entity;


import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String password;
}
