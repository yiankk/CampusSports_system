package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class BorrowPageRequest extends BaseRequest{
    private String sportsName;
    private String sportsNo;
    private String userName;
}
