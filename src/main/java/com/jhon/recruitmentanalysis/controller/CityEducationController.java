package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.service.CityEducationService;
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

@RestController
@CrossOrigin
public class CityEducationController {

    @Resource
    CityEducationService cityEducationService;

    @PostMapping("/getCityEducation")
    public List<Map> getCityEducation(@RequestParam(value = "city", required = false) ArrayList<String> city){
        List<Map> list = new ArrayList<>();
        List<Map> cityEducation;
        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            cityEducation = cityEducationService.getCityEducation();
        } else {
            cityEducation = cityEducationService.getCityEducation(city);
        }
        for (Map map : cityEducation) {
            Map<String,Object> objectMap = new LinkedHashMap<>();
            objectMap.put("name",map.get("EDUCATION_BACKGROUND"));
            objectMap.put("value",map.get("EDUCATION_NUM"));
            list.add(objectMap);
        }
        return list;
    }

    @PostMapping("/getEducationBySalary")
    public Map<String,Object> getEducationBySalary(@RequestParam(value = "minSalary", required = false) BigDecimal minSalary,
                                                   @RequestParam(value = "maxSalary", required = false) BigDecimal maxSalary){
        if (minSalary == null){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入最低薪资");
            return returnMap;
        }
        if (maxSalary == null){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入最高薪资");
            return returnMap;
        }
        List<Map> educationBySalary = cityEducationService.getEducationBySalary(minSalary, maxSalary);
        List list = new ArrayList();
        for (Map map : educationBySalary) {
            Map newMap = new LinkedHashMap();
            newMap.put("name",map.get("EDUCATION_BACKGROUND"));
            newMap.put("value",map.get("EDUCATION_NUM"));
            list.add(newMap);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("data",list);
        return map;
    }

    @PostMapping("/getCityBySalary")
    public Map<String,Object> getCityBySalary(@RequestParam(value = "minSalary", required = false) BigDecimal minSalary,
                                              @RequestParam(value = "maxSalary", required = false) BigDecimal maxSalary){
        if (minSalary == null){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入最低薪资");
            return returnMap;
        }
        if (maxSalary == null){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入最高薪资");
            return returnMap;
        }
        List<Map> cityBySalary = cityEducationService.getCityBySalary(minSalary, maxSalary);
        List list = new ArrayList();
        for (Map map : cityBySalary) {
            Map newMap = new LinkedHashMap();
            newMap.put("name",map.get("CITY"));
            newMap.put("value",map.get("CITY_NUM"));
            list.add(newMap);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("data",list);
        return map;
    }

    //所选区域招聘岗位Top10
    @PostMapping("/getPositionNumByCity")
    public Map<String,Object> getPositionNumByCity(@RequestParam(value = "city", required = false) ArrayList<String> city){
        List<Map> positionNumByCity;
        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionNumByCity = cityEducationService.getPositionNumByCity();
        } else {
            positionNumByCity = cityEducationService.getPositionNumByCity(city);
        }
        List positionList = new ArrayList();
        List positionNumList = new ArrayList();
        for (Map map : positionNumByCity) {
            positionList.add(map.get("POSITION"));
            positionNumList.add(map.get("POSITION_NUM"));
        }
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("name",positionList);
        map.put("value",positionNumList);
        return map;
    }

}
