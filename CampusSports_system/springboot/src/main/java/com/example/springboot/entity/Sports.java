package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class Sports extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类
     */
    private String category;

    /**
     * 器材码
     */
    private String sportsNo;


    private String cover;

    private List<String> categories;
    private Integer nums;

}

