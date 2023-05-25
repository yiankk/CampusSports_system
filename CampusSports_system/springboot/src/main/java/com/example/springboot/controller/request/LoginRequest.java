package com.example.springboot.controller.request;

import lombok.Data;
import org.springframework.web.util.pattern.PathPattern;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
