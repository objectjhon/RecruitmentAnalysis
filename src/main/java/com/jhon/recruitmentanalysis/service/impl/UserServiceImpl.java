package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.UserMapper;
import com.jhon.recruitmentanalysis.pojo.User;
import com.jhon.recruitmentanalysis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getAllUserByName(String name) {
        return userMapper.getAllUserByName(name);
    }

    @Override
    public String getPwdByName(String name) {
        return userMapper.getPwdByName(name);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> getAllUser(String query) {
        return userMapper.getAllUser(query);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Integer updateUser(String name, String password) {
        return userMapper.updateUser(name,password);
    }

}
