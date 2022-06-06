package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePay;

import java.util.List;

public interface JobOnlinePayService {

    List<JobOnlinePay> findAllJobOnlinePay(Integer limit);

    List<JobOnlinePay> findAllJobOnlinePay(Integer limit, List<String> city);

}
