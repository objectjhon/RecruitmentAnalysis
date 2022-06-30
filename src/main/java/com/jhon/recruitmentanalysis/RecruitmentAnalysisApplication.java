package com.jhon.recruitmentanalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jhon.recruitmentanalysis.mapper")
public class RecruitmentAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentAnalysisApplication.class, args);
    }

}
