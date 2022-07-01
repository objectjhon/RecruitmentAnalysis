package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.User;

import java.util.List;

public interface UserService {

    User getAllUserByName(String name);

    String getPwdByName(String name);

    Integer insertUser(User user);

    List<User> getAllUser(String query);

    User getUserById(Integer id);

    Integer deleteUser(Integer id);

    Integer updateUser(String name,String password);

}
