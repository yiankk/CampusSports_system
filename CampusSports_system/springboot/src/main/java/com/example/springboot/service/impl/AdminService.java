package com.example.springboot.service.impl;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Admin> users = adminMapper.listByCondition(baseRequest);  //条件查询
        return new PageInfo<>(users);
    }

    @Override
    public void save(Admin obj) {
        //Date date = new Date();
        //当作卡号处理
        //obj.setUsername(DateUtil.format(date,"yyyMMdd")+Math.abs(IdUtil.fastSimpleUUID().hashCode()
        //));
        adminMapper.save(obj);

    }

    @Override
    public Admin getById(Integer id) {

        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin user) {
        adminMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
      Admin admin = adminMapper.getByUsernameAndPassword(request);
      if(admin == null){
          throw new ServiceException("用户名或密码错误");
      }
      LoginDTO loginDTO = new LoginDTO();
      BeanUtils.copyProperties(admin, loginDTO);
      return loginDTO;
    }

}
