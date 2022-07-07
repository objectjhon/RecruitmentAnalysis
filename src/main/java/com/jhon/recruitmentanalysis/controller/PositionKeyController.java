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

        List<Map<String,Object>> allKeyValue = positionKeyService.getAllKeyValue(city);

        List<Map> mapList = new ArrayList<>();

        for (Map<String, Object> stringIntegerMap : allKeyValue) {
            Map<String, Object> map1 = new LinkedHashMap<>();
            map1.put("name",stringIntegerMap.get("KEYWORD"));
            map1.put("value",stringIntegerMap.get("KEYWORD_COUNT"));
            mapList.add(map1);
        }

        List<Map> collect = mapList.stream().
                sorted((h1, h2) -> ((Long) h2.get("value")).compareTo((Long) h1.get("value"))).collect(Collectors.toList());

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("data",collect);

        return map;

    }


    @PostMapping("/getSomeKeyValue")
    public Map<String, Object> getSomeKeyValue(){

        List<Map<String,Object>> allKeyValue = positionKeyService.getAllKeyValue();

        List<Map> mapList = new ArrayList<>();

        for (Map<String, Object> stringIntegerMap : allKeyValue) {
            Map<String, Object> map1 = new LinkedHashMap<>();
            map1.put("name",stringIntegerMap.get("KEYWORD"));
            map1.put("value",stringIntegerMap.get("KEYWORD_COUNT"));
            mapList.add(map1);
        }

        List<Map> collect = mapList.stream().
                sorted((h1, h2) -> ((Long) h2.get("value")).compareTo((Long) h1.get("value"))).limit(10).collect(Collectors.toList());

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("max",collect.get(0).get("value"));

        map.put("data",collect);

        return map;

    }


    //某岗位专业术语提到的次数
    @PostMapping("/getPositionKeyValue")
    public Map<String,Object> getPositionKeyValue(@RequestParam(value = "limit", required = false) Integer limit,
                                                  @RequestParam(value = "position", required = false) String position,
                                                  @RequestParam(value = "city", required = false) ArrayList<String> city){

        if (position == null){
            position = positionNameService.getHighestPositionCount(null,null).getPosition();
        }

        List<Map<String,Object>> positionKeyValue;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionKeyValue = positionKeyService.getPositionKeyValue(limit,position);
        } else {
            positionKeyValue = positionKeyService.getPositionKeyValue(limit,position, city);
        }

        List keyList = new ArrayList();
        List valueList = new ArrayList();

        for (Map<String, Object> map : positionKeyValue) {
            keyList.add(map.get("KEYWORD"));
            valueList.add(map.get("KEYWORD_COUNT"));
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("max",positionKeyValue.get(0).get("KEYWORD_COUNT"));

        map.put("nameList",keyList);

        Map<String,Object> dataMap = new LinkedHashMap<>();
        dataMap.put("name",position);
        dataMap.put("value",valueList);
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

    @PostMapping("/getKeyByPosition")
    public Map<String,Object> getKeyByPosition(@RequestParam(value = "position", required = false) ArrayList<String> position){

        if (position == null || position.size() == 0){
            Map<String,Object> returnMap = new LinkedHashMap<>();
            returnMap.put("code",404);
            returnMap.put("message","请输入岗位列表");
            return returnMap;
        }

        List<String> allKey = positionKeyService.getAllKey(position);

        List<Map<String, Object>> keyByPosition = positionKeyService.getKeyByPosition(position);

        LinkedHashMap<String, List<Map<String, Object>>> hashMap = keyByPosition.stream()
                .collect(Collectors.groupingBy(e -> e.get("POSITION").toString(), LinkedHashMap::new, Collectors.toList()));

        List<Map<String,Object>> list = new ArrayList();

        for (Map.Entry<String, List<Map<String, Object>>> stringListEntry : hashMap.entrySet()) {
            Map<String,Object> objectMap = new LinkedHashMap<>();
            objectMap.put("name",stringListEntry.getKey());
            String keyword = "";
            for (int i = 0;i < stringListEntry.getValue().size();i++){
                if (i < stringListEntry.getValue().size() - 1){
                    keyword = keyword.concat((String) stringListEntry.getValue().get(i).get("KEYWORD")).concat("|");
                } else {
                    keyword = keyword.concat((String) stringListEntry.getValue().get(i).get("KEYWORD"));
                }
            }
            objectMap.put("keyword",keyword);
            list.add(objectMap);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        Map<String,Object> newMap = new LinkedHashMap();
        newMap.put("keywordlist",allKey);
        newMap.put("positionKeyword",list);
        map.put("data",newMap);
        return map;
    }

    @PostMapping("/getKeyWordBySalary")
    public Map<String,Object> getKeyWordBySalary(@RequestParam(value = "minSalary", required = false) BigDecimal minSalary,
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
        String salary = minSalary+"-"+maxSalary;
        List<Map> keyWordBySalary = positionKeyService.getKeyWordBySalary(salary);
        List<Map> list = new ArrayList();
        for (Map map : keyWordBySalary) {
            Map newMap = new LinkedHashMap();
            newMap.put("name",map.get("KEYWORD"));
            newMap.put("value",map.get("KEYWORD_COUNT"));
            list.add(newMap);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("max",list.get(0).get("value"));
        map.put("data",list);
        return map;
    }

}
