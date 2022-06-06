package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JobOnlinePayMinMax {

    private String position;
    private double payMin;
    private double payMax;

}
