package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PositionSalary {

    private String year;
    private String month;
    private String position;
    private BigDecimal salaryMax;
    private BigDecimal salaryMin;
    private BigDecimal salaryAvg;

}
