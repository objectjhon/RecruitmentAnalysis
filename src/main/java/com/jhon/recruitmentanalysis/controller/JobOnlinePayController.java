package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePay;
import com.jhon.recruitmentanalysis.service.JobOnlinePayService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class JobOnlinePayController {

    @Resource
    private JobOnlinePayService jobOnlinePayService;

    @GetMapping("/findAllJobOnlinePay")
    public R findAllJobOnlinePay(@RequestParam(value = "limit", required = false) Integer limit){
        List<JobOnlinePay> allJobOnlinePay = jobOnlinePayService.findAllJobOnlinePay(limit);

        System.out.println("----"+limit);

        List<Object> allJobOnlinePayList = new ArrayList<>();

        for (JobOnlinePay jobOnlinePay : allJobOnlinePay) {
            DecimalFormat df = new DecimalFormat("######0.00");
            String payMax = jobOnlinePay.getPayMax()+"";
            String payMin = ""+jobOnlinePay.getPayMin();
            String payAvg = df.format(jobOnlinePay.getPayAvg());
            String str[] = {jobOnlinePay.getPosition(),payMax,payMin,payAvg};
            allJobOnlinePayList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        String str1[] = {"最高工资","最低工资","平均工资"};

        map.put("name",str1);

        map.put("allJobOnlinePayList",allJobOnlinePayList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/findAllJobOnlinePay")
    public R postFindAllJobOnlinePay(@RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "city",required = false) ArrayList<String> city){

        List<JobOnlinePay> allJobOnlinePay;

        System.out.println("-----"+limit);

        System.out.println(city);

        if (city != null){

            allJobOnlinePay = jobOnlinePayService.findAllJobOnlinePay(limit,city);

        } else {

            allJobOnlinePay = jobOnlinePayService.findAllJobOnlinePay(limit);

        }

        List<Object> allJobOnlinePayList = new ArrayList<>();

        for (JobOnlinePay jobOnlinePay : allJobOnlinePay) {
            DecimalFormat df = new DecimalFormat("######0.00");
            String payMax = jobOnlinePay.getPayMax()+"";
            String payMin = ""+jobOnlinePay.getPayMin();
            String payAvg = df.format(jobOnlinePay.getPayAvg());
            String str[] = {jobOnlinePay.getPosition(),payMax,payMin,payAvg};
            allJobOnlinePayList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        String str1[] = {"最高工资","最低工资","平均工资"};

        map.put("name",str1);

        map.put("allJobOnlinePayList",allJobOnlinePayList);

        return R.ok().message("查询成功").data(map);
    }

}
