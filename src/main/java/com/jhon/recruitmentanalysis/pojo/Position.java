package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Position {

    private String position;
    private String year;
    private String month;
    private String day;
    private String city;
    private String keywords;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String companyType;
    private String companyScale;
    private String addressDetail;
    private String treatment;

}
