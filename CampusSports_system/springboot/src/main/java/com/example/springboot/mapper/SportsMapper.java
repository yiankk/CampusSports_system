package com.example.springboot.mapper;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Sports;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SportsMapper {
    List<Sports> list();

    List<Sports> listByCondition(BaseRequest baseRequest);

    void save(Sports obj);

    Sports getById(Integer id);

    void updateById(Sports user);

    void deleteById(Integer id);


    Sports getByNo(String sportsNo);

    void updateNumByNo(String sportsNo);
}