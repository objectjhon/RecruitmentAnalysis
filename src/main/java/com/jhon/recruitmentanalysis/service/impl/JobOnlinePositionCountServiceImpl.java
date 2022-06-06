package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.JobOnlinePositionCountMapper;
import com.jhon.recruitmentanalysis.pojo.JobOnlinePositionCount;
import com.jhon.recruitmentanalysis.service.JobOnlinePositionCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobOnlinePositionCountServiceImpl implements JobOnlinePositionCountService {

    @Resource
    private JobOnlinePositionCountMapper jobOnlinePositionCountMapper;

    @Override
    public List<JobOnlinePositionCount> findAllJobOnlinePositionCount() {
        return jobOnlinePositionCountMapper.findAllJobOnlinePositionCount();
    }

    @Override
    public List<JobOnlinePositionCount> postFindAllJobOnlinePositionCount(List<String> city) {
        return jobOnlinePositionCountMapper.postFindAllJobOnlinePositionCount(city);
    }

}
