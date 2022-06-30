package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.service.PositionKeyService;
import com.jhon.recruitmentanalysis.service.PositionNameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PositionKeyController {

    @Resource
    PositionKeyService positionKeyService;

    @Resource
    PositionNameService positionNameService;

    @PostMapping("/getAllKeyValue")
    public Map<String, Object> getAllKeyValue(@RequestParam(value = "city", required = false) ArrayList<String> city){

        Map<String, Integer> allKeyValue = positionKeyService.getAllKeyValue(city);

        List<Map> mapList = new ArrayList<>();

        for (String s : allKeyValue.keySet()) {
            Map<String, Object> map1 = new LinkedHashMap<>();
            map1.put("name",s);
            map1.put("value",allKeyValue.get(s));
            mapList.add(map1);
        }

        List<Map> collect = mapList.stream().
                sorted((h1, h2) -> ((Integer) h2.get("value")).compareTo((Integer) h1.get("value"))).collect(Collectors.toList());

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("data",collect);

        return map;

    }

    //某岗位专业术语提到的次数
    @PostMapping("/getPositionKeyValue")
    public Map<String,Object> getPositionKeyValue(@RequestParam(value = "position", required = false) String position,
                                                  @RequestParam(value = "city", required = false) ArrayList<String> city){

        if (position == null){
            position = positionNameService.getHighestPositionCount(null,null).getPosition();
        }

        Map<String, Integer> positionKeyValue;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionKeyValue = positionKeyService.getPositionKeyValue(position);
        } else {
            positionKeyValue = positionKeyService.getPositionKeyValue(position, city);
        }

        LinkedHashMap<String, Integer> hashMap = positionKeyValue.entrySet().stream().sorted((e1, e2) -> {
            return e2.getValue() - e1.getValue();
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("max",hashMap.values().toArray()[0]);

        map.put("nameList",hashMap.keySet());

        Map<String,Object> dataMap = new LinkedHashMap<>();
        dataMap.put("name",position);
        dataMap.put("value",hashMap.values());
        map.put("data",dataMap);

        return map;

    }

    //查询某工资范围某岗位专业术语
    @PostMapping("/getKeyBySalaryPosition")
    public Map<String,Object> getKeyBySalaryPosition(@RequestParam(value = "position", required = false) String position,
                                                     @RequestParam(value = "salaryMin", required = false) BigDecimal salaryMin,
                                                     @RequestParam(value = "salaryMax", required = false) BigDecimal salaryMax,
                                                     @RequestParam(value = "city", required = false) ArrayList<String> city){

        if (position == null){
            position = positionNameService.getHighestPositionCount(null,null).getPosition();
        }

        Map<String,Integer> keyMap = positionKeyService.getKeyBySalaryPosition(position,salaryMin,salaryMax,city);

        LinkedHashMap<String, Integer> hashMap = keyMap.entrySet().stream().sorted((e1, e2) -> {
            return e2.getValue() - e1.getValue();
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<String,Object> map = new LinkedHashMap<>();

        if (hashMap.values().toArray().length > 0){
            map.put("max",hashMap.values().toArray()[0]);
        } else {
            map.put("max","无最大值");
        }

        map.put("nameList",hashMap.keySet());

        Map<String,Object> dataMap = new LinkedHashMap<>();
        dataMap.put("name",position);
        dataMap.put("value",hashMap.values());
        map.put("data",dataMap);

        return map;

    }

}
