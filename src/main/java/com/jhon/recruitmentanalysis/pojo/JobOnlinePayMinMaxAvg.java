package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JobOnlinePayMinMaxAvg {

    private String position;
//    private double payMinAvg;
//    private double payMaxAvg;
    private double payAvg;

}
