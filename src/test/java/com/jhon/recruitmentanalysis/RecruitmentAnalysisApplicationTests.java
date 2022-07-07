package com.jhon.recruitmentanalysis;

import com.jhon.recruitmentanalysis.mapper.UserMapper;
import com.jhon.recruitmentanalysis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class RecruitmentAnalysisApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {

        List<User> allUser = userMapper.getAllUser("");
        for (User user : allUser) {
            System.out.println(user.toString());
        }


    }

}
