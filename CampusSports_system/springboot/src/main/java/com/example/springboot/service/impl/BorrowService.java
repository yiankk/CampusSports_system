package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.entity.Sports;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.SportsMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BorrowService implements IBorrowService {

    @Resource
    BorrowMapper borrowMapper;

    @Autowired
    SportsMapper sportsMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow borrow : borrows) {
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if (now.plusDays(1).isEqual(returnDate)) {  // 当前日期比归还的日期小一天
                borrow.setNote("即将到期");
            } else if (now.isEqual(returnDate)) {
                borrow.setNote("已到期");
            } else if (now.isAfter(returnDate)) {
                borrow.setNote("已过期");
            } else {
                borrow.setNote("正常");
            }
        }

        return new PageInfo<>(borrows);
    }

    @Override
    @Transactional //保证事务同时更新成功或者同时更新失败
    public void save(Borrow obj)
    {
        //校验图书的数量是否足够
        Sports sports = sportsMapper.getByNo(obj.getSportsNo());
        if(Objects.isNull(sports)){
            throw new ServiceException("所借器材不存在");
        }

        if(sports.getNums() < 1){
            throw new ServiceException("器材数量不足");
        }

        //更新图书数量
        sports.setNums(sports.getNums() - 1);
        sportsMapper.updateById(sports);

        obj.setReturnDate(LocalDate.now().plus(obj.getDays(), ChronoUnit.DAYS)); //当前的日期加 days 得到归还的日期
        //新增借书纪录
        borrowMapper.save(obj);
    }

    @Override
    public PageInfo<Retur> pageRetur(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        return new PageInfo<>(borrowMapper.listReturByCondition(baseRequest));
    }


    @Override
    @Transactional
    public void saveRetur(Retur obj) {
        // 改状态
        obj.setStatus("已归还");
        borrowMapper.updateStatus("已归还", obj.getId());  //obj.getId()是前端传来的借出id

        obj.setRealDate(LocalDate.now());

        //图书数量增加
        borrowMapper.saveRetur(obj);

        sportsMapper.updateNumByNo(obj.getSportsNo());
        //判断归还日期
        User user = userMapper.getByUsername(obj.getUserNo());
        if(obj.getRealDate().isAfter(obj.getReturnDate())){
            user.setStatus(false);
        }
        userMapper.updateById(user);

    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow obj) {
        obj.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(obj);

    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void deleteReturById(Integer id) {
        borrowMapper.deleteReturById(id);
    }

}
