package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Sports;
import com.example.springboot.mapper.SportsMapper;
import com.example.springboot.service.ISportsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class SportsService implements ISportsService {

    @Resource
    SportsMapper sportsMapper;

    @Override
    public List<Sports> list() {
        return sportsMapper.list();
    }

    @Override
    public PageInfo<Sports> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        return new PageInfo<>(sportsMapper.listByCondition(baseRequest));
    }

    @Override
    public void save(Sports obj)
    {
        obj.setCategory(category(obj.getCategories()));
        sportsMapper.save(obj);
    }

    @Override
    public Sports getById(Integer id) {
        return sportsMapper.getById(id);
    }

    @Override
    public void update(Sports obj) {
        obj.setCategory(category(obj.getCategories()));
        obj.setUpdatetime(LocalDate.now());
        sportsMapper.updateById(obj);

    }

    @Override
    public void deleteById(Integer id) {
        sportsMapper.deleteById(id);
    }

    private String category(List<String> categories){
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v ->sb.append(v).append(" > "));
            return sb.substring(0,sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }
}
