package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JobOnlinePay {

    private String position;
    private double payMin;
    private double payMax;
    private double payAvg;

}
