package com.jhon.recruitmentanalysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.Position;
import com.jhon.recruitmentanalysis.service.PositionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PositionController {

    @Resource
    PositionService positionService;

    @PostMapping("/getAll")
    public Map<String,Object> getAll(@RequestParam(value = "pageNum", required = false) Integer pageNum,
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

        PageInfo pageInfo = new PageInfo(positionList);

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

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("pages",pageInfo.getPages());
        map.put("data",list);

        return map;

    }

    @PostMapping("/getCountByPositionDate")
    public Map<String,Object> getCountByPositionDate(@RequestParam(value = "position", required = false) ArrayList<String> position,
                                                     @RequestParam(value = "city", required = false) ArrayList<String> city){

        if (position == null || position.size() == 0){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入岗位列表");
            return returnMap;
        }

        if (city == null || city.size() == 0){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入城市列表");
            return returnMap;
        }

        List<Map<String, Object>> countByPositionDate = positionService.getCountByPositionDate(position, city);

        List<List> list = new ArrayList();

        for (Map<String, Object> map : countByPositionDate) {
            List newList = new ArrayList();
            newList.add(map.get("YEAR")+"-"+map.get("MONTH"));
            newList.add(map.get("POSITION"));
            newList.add(map.get("ALL_NUM"));
            newList.add(map.get("CITY_NUM"));
            list.add(newList);
        }

        LinkedHashMap<String, List<List>> hashMap = list.stream()
                .collect(Collectors.groupingBy(e -> e.get(0).toString(), LinkedHashMap::new, Collectors.toList()));

        for (List<List> value : hashMap.values()) {
            for (List list1 : value) {
                list1.remove(0);
            }
        }

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("date",hashMap.keySet());
        map.put("data",hashMap);

        return map;

    }

}
