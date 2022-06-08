package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMax;
import com.jhon.recruitmentanalysis.service.JobOnlinePayMinMaxService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@CrossOrigin
public class JobOnlinePayMinMaxController {

    @Resource
    private JobOnlinePayMinMaxService jobOnlinePayMinMaxService;

    @GetMapping("/findAllJobOnlinePayMinMax")
    public R findAllJobOnlinePayMinMax(@RequestParam(value = "limit", required = false) Integer limit){
        List<JobOnlinePayMinMax> allJobOnlinePayMinMax = jobOnlinePayMinMaxService.findAllJobOnlinePayMinMax(limit);

        List<Object> allJobOnlinePayMinMaxList = new ArrayList<>();

        for (JobOnlinePayMinMax jobOnlinePayMinMax : allJobOnlinePayMinMax) {
            Double max = new Double(jobOnlinePayMinMax.getPayMax());
            int payMax = max.intValue();
            Double min = new Double(jobOnlinePayMinMax.getPayMin());
            int payMin = min.intValue();
            String str[] = {jobOnlinePayMinMax.getPosition(),payMax+"","-"+payMin};
            allJobOnlinePayMinMaxList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        String str1[] = {"最高工资","最低工资"};

        map.put("name",str1);

        map.put("allJobOnlinePayMinMaxList",allJobOnlinePayMinMaxList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/findAllJobOnlinePayMinMax")
    public R postFindAllJobOnlinePayMinMax(@RequestParam(value = "limit", required = false) Integer limit,
                                           @RequestParam(value = "city",required = false) ArrayList<String> city){

        List<JobOnlinePayMinMax> allJobOnlinePayMinMax;

        System.out.println("-----"+limit);

        if (city != null){

            allJobOnlinePayMinMax = jobOnlinePayMinMaxService.findAllJobOnlinePayMinMax(limit, city);

        } else {

            allJobOnlinePayMinMax = jobOnlinePayMinMaxService.findAllJobOnlinePayMinMax(limit);

        }

        List<Object> allJobOnlinePayMinMaxList = new ArrayList<>();

        for (JobOnlinePayMinMax jobOnlinePayMinMax : allJobOnlinePayMinMax) {
            Double max = new Double(jobOnlinePayMinMax.getPayMax());
            int payMax = max.intValue();
            Double min = new Double(jobOnlinePayMinMax.getPayMin());
            int payMin = min.intValue();
            String str[] = {jobOnlinePayMinMax.getPosition(),payMax+"","-"+payMin};
            allJobOnlinePayMinMaxList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        String str1[] = {"最高工资","最低工资"};

        map.put("name",str1);

        map.put("allJobOnlinePayMinMaxList",allJobOnlinePayMinMaxList);

        return R.ok().message("查询成功").data(map);
    }

}
