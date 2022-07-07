package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.service.CompanyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CompanyController {

    @Resource
    CompanyService companyService;

    @PostMapping("/getCompanyScale")
    public Map getCompanyScale(@RequestParam(value = "position", required = false) ArrayList<String> position){
        if (position == null || position.size() == 0){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入岗位列表");
            return returnMap;
        }
        List<Map> companyScale = companyService.getCompanyScale(position);
        List list = new ArrayList();
        for (Map map : companyScale) {
            Map newMap = new LinkedHashMap();
            newMap.put("name",map.get("COMPANY_SCALE"));
            newMap.put("value",map.get("SCALE_NUM"));
            list.add(newMap);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("data",list);
        return map;
    }

    @PostMapping("/getCompanyType")
    public Map getCompanyType(@RequestParam(value = "position", required = false) ArrayList<String> position){
        if (position == null || position.size() == 0){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入岗位列表");
            return returnMap;
        }
        List<Map> companyScale = companyService.getCompanyType(position);
        List<Map> list = new ArrayList();
        for (Map map : companyScale) {
            Map newMap = new LinkedHashMap();
            newMap.put("name",map.get("COMPANY_TYPE"));
            newMap.put("value",map.get("TYPE_NUM"));
            list.add(newMap);
        }
        List newList = new ArrayList();
        newList = list.subList(0,5);
        Long sum = 0L;
        for (int i = 5;i < list.size();i++){
            sum += (Long) list.get(i).get("value");
        }
        Map<String,Object> otherMap = new LinkedHashMap<>();
        otherMap.put("name","其他");
        otherMap.put("value",sum);
        newList.add(otherMap);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("data",newList);
        return map;
    }

}
