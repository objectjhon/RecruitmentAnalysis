package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMaxAvg;
import com.jhon.recruitmentanalysis.service.JobOnlinePayMinMaxAvgService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@CrossOrigin
public class JobOnlinePayMinMaxAvgController {

    @Resource
    private JobOnlinePayMinMaxAvgService jobOnlinePayMinMaxAvgService;

    @GetMapping("/findAllJobOnlinePayMinMaxAvg")
    public R findAllJobOnlinePayMinMaxAvg(@RequestParam(value = "limit", required = false) Integer limit){
        List<JobOnlinePayMinMaxAvg> allJobOnlinePayMinMaxAvg = jobOnlinePayMinMaxAvgService.findAllJobOnlinePayMinMaxAvg(limit);

        List<Object> allJobOnlinePayMinMaxAvgList = new ArrayList<>();

        for (JobOnlinePayMinMaxAvg jobOnlinePayMinMaxAvg : allJobOnlinePayMinMaxAvg) {
            DecimalFormat df = new DecimalFormat("######0.00");
            String payAvg = df.format(jobOnlinePayMinMaxAvg.getPayAvg());
            String str[] = {jobOnlinePayMinMaxAvg.getPosition(),payAvg};
            allJobOnlinePayMinMaxAvgList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","平均工资");

        map.put("allJobOnlinePayMinMaxAvgList",allJobOnlinePayMinMaxAvgList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/findAllJobOnlinePayMinMaxAvg")
    public R postFindAllJobOnlinePayMinMaxAvg(@RequestParam(value = "limit", required = false) Integer limit,
                                              @RequestParam(value = "city",required = false) List<String> city){

        List<JobOnlinePayMinMaxAvg> allJobOnlinePayMinMaxAvg;

        if (city != null){

            allJobOnlinePayMinMaxAvg = jobOnlinePayMinMaxAvgService.findAllJobOnlinePayMinMaxAvg(limit,city);

        } else {

            allJobOnlinePayMinMaxAvg = jobOnlinePayMinMaxAvgService.findAllJobOnlinePayMinMaxAvg(limit);

        }

        List<Object> allJobOnlinePayMinMaxAvgList = new ArrayList<>();

        for (JobOnlinePayMinMaxAvg jobOnlinePayMinMaxAvg : allJobOnlinePayMinMaxAvg) {
            DecimalFormat df = new DecimalFormat("######0.00");
            String payAvg = df.format(jobOnlinePayMinMaxAvg.getPayAvg());
            String str[] = {jobOnlinePayMinMaxAvg.getPosition(),payAvg};
            allJobOnlinePayMinMaxAvgList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","平均工资");

        map.put("allJobOnlinePayMinMaxAvgList",allJobOnlinePayMinMaxAvgList);

        return R.ok().message("查询成功").data(map);
    }

}
