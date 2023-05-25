package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class SportsPageRequest extends BaseRequest{
    private String name;
    private String sportsNo;
}
