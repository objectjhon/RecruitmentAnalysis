package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePositionCount;
import com.jhon.recruitmentanalysis.service.JobOnlinePositionCountService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@CrossOrigin
public class JobOnlinePositionCountController {

    @Resource
    private JobOnlinePositionCountService jobOnlinePositionCountService;

    @GetMapping("/findAllJobOnlinePositionCount")
    public R getFindAllJobOnlinePositionCount(){
        List<JobOnlinePositionCount> allJobOnlinePositionCount = jobOnlinePositionCountService.findAllJobOnlinePositionCount();

        List<Object> mapList = new ArrayList<>();

        for (JobOnlinePositionCount jobOnlinePositionCount : allJobOnlinePositionCount) {

            String str[] = {jobOnlinePositionCount.getPosition(),jobOnlinePositionCount.getPositionCount().toString()};

            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allJobOnlinePositionCount",mapList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/findAllJobOnlinePositionCount")
    public R postFindAllJobOnlinePositionCount(@RequestBody(required = false) List<String> city){

        List<JobOnlinePositionCount> allJobOnlinePositionCount;

        List<Object> mapList = new ArrayList<>();

        if (city != null){

            allJobOnlinePositionCount = jobOnlinePositionCountService.postFindAllJobOnlinePositionCount(city);

        } else {

            allJobOnlinePositionCount = jobOnlinePositionCountService.findAllJobOnlinePositionCount();

        }

        for (JobOnlinePositionCount jobOnlinePositionCount : allJobOnlinePositionCount) {

            String str[] = {jobOnlinePositionCount.getPosition(),jobOnlinePositionCount.getPositionCount().toString()};

            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allJobOnlinePositionCount",mapList);

        return R.ok().message("查询成功").data(map);
    }

}
