package com.jhon.recruitmentanalysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.Position;
import com.jhon.recruitmentanalysis.service.PositionService;
import javafx.geometry.Pos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PositionController {

    @Resource
    PositionService positionService;

    @PostMapping("/getAll")
    public List<Map<String,Object>> getAll(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                           @RequestParam(value = "date", required = false) String date,
                                           @RequestParam(value = "city", required = false) String city,
                                           @RequestParam(value = "keyword", required = false) ArrayList<String> keyword,
                                           @RequestParam(value = "salary", required = false) ArrayList<BigDecimal> salary){

        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }
        String year;
        String month;
        String day;
        if (date == null || date == "") {
            year = null;
            month = null;
            day = null;
        } else {
            year = date.split("-")[0];
            month = date.split("-")[1];
            day = date.split("-")[2];
        }
        BigDecimal salaryMin;
        BigDecimal salaryMax;
        if (salary == null || salary.size() == 0) {
            salaryMin = null;
            salaryMax = null;
        } else {
            salaryMin = salary.get(0);
            salaryMax = salary.get(1);
        }

        PageHelper.startPage(pageNum,pageSize);

        List<Position> positionList;

        if (keyword == null || keyword.size()==0){
            positionList = positionService.getAll(year,month,day,city,salaryMin,salaryMax);
        } else {
            positionList = positionService.getAll(year,month,day,city,salaryMin,salaryMax,keyword);
        }

        List<Map<String,Object>> list = new ArrayList<>();

        for (Position position : positionList) {
            Map<String,Object> objectMap = new LinkedHashMap<>();
            objectMap.put("name",position.getPosition());
            objectMap.put("date",position.getYear()+"-"+position.getMonth()+"-"+position.getDay());
            objectMap.put("city",position.getCity());
            objectMap.put("keyword",position.getKeywords());
            objectMap.put("salary",position.getSalaryMin()
                    .intValue()/1000+"k-"+position.getSalaryMax().intValue()/1000+"k");
            list.add(objectMap);
        }

        PageInfo pageInfo = new PageInfo(list);
        System.out.println(pageInfo);

        return list;

    }

}
