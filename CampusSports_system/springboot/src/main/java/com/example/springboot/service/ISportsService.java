package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Sports;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISportsService {
    List<Sports> list();

    PageInfo<Sports> page(BaseRequest baseRequest);

    void save(Sports obj);

    Sports getById(Integer id);

    void update(Sports obj);

    void deleteById(Integer id);
}
