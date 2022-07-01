package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getAllUserByName(@Param("name") String name);

    String getPwdByName(@Param("name") String name);

    Integer insertUser(User user);

    List<User> getAllUser(@Param("query") String query);

    User getUserById(@Param("id") Integer id);

    Integer deleteUser(@Param("id") Integer id);

    Integer updateUser(@Param("name") String name,
                       @Param("password") String password);

}
