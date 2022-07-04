package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.PositionName;
import com.jhon.recruitmentanalysis.service.PositionNameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PositionNameController {

    @Resource
    PositionNameService positionNameService;

    //获取岗位名称及招聘数量
    @PostMapping("/getAllPositionName")
    public Map getAllPositionName(@RequestParam(value = "limit", required = false) Integer limit,
                                  @RequestParam(value = "city", required = false) ArrayList<String> city){

        //获取所有的岗位
        List<PositionName> allPositionName;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            allPositionName = positionNameService.getAllPositionName();
        } else {
            allPositionName = positionNameService.getAllPositionName(city);
        }

        List<Map> list1 = new ArrayList();

        //遍历生成新的map放入list1
        for (PositionName positionName : allPositionName) {
            Map<String,Object> map1 = new LinkedHashMap<>();
            map1.put("date",positionName.getYear()+"-"+positionName.getMonth());
            map1.put("name",positionName.getPosition());
            map1.put("value",positionName.getPositionCount());
            list1.add(map1);
        }

        //map根据日期分组
        Map<String,List<Map>> mapListGroup = list1.stream()
                .collect(Collectors.groupingBy(e -> e.get("date").toString(),LinkedHashMap::new,Collectors.toList()));

        //过滤日期,限制返回个数
        for (Map.Entry<String, List<Map>> stringListEntry : mapListGroup.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                mapListGroup.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (Map map : stringListEntry.getValue()) {
                map.remove("date");
            }
        }

        Map<String,Object> map = new LinkedHashMap();

        map.put("date",mapListGroup.keySet());

        map.put("data",mapListGroup);

        return map;

    }

    //获取招聘岗位数量最多的岗位
    @PostMapping("/getHighestPositionCount")
    public Map<String,Object> getHighestPositionCount(@RequestParam(value = "date", required = false) String date,
                                                      @RequestParam(value = "city", required = false) ArrayList<String> city){

        PositionName positionName;

        String year;
        String month;

        if (date == null){
            year = null;
            month = null;
        } else {
            year = date.split("-")[0];
            month = date.split("-")[1];
        }

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionName = positionNameService.getHighestPositionCount(year,month);
        } else {
            positionName = positionNameService.getHighestPositionCount(year,month,city);
        }

        Map<String,Object> map = new LinkedHashMap();

        map.put(positionName.getPosition(), positionName.getPositionCount());

        return map;

    }

    //获取招聘岗位数量最多的城市
    @PostMapping("/getHighestCityPosition")
    public Map getHighestCityPosition(@RequestParam(value = "date", required = false) String date,
                                      @RequestParam(value = "city", required = false) ArrayList<String> city){

        Map<String, Object> highestCityPosition;

        String year;
        String month;

        if (date == null){
            year = null;
            month = null;
        } else {
            year = date.split("-")[0];
            month = date.split("-")[1];
        }

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            highestCityPosition = positionNameService.getHighestCityPosition(year,month);
        } else {
            highestCityPosition = positionNameService.getHighestCityPosition(year,month,city);
        }

        Map map = new LinkedHashMap();

        map.put(highestCityPosition.get("CITY"),highestCityPosition.get("POSITION_COUNT"));

        return map;

    }

    //统计每个城市招聘岗位的数量
    @PostMapping("/getCityPosition")
    public Map getCityPosition(@RequestParam(value = "limit", required = false) Integer limit,
                               @RequestParam(value = "city", required = false) ArrayList<String> city){

        //获取所有的城市岗位数量
        List<Map<String, Object>> cityPosition;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            cityPosition = positionNameService.getCityPosition();
        } else {
            cityPosition = positionNameService.getCityPosition(city);
        }

        List<Map> list1 = new ArrayList();

        //遍历生成新的map放入list1
        for (Map<String, Object> map : cityPosition) {
            Map<String,Object> map1 = new LinkedHashMap<>();
            map1.put("date",map.get("YEAR")+"-"+map.get("MONTH"));
            map1.put("name",map.get("CITY"));
            map1.put("value",map.get("POSITION_COUNT"));
            list1.add(map1);
        }

        //map根据日期分组
        LinkedHashMap<String, List<Map>> hashMap = list1.stream()
                .collect(Collectors.groupingBy(e -> e.get("date").toString(), LinkedHashMap::new, Collectors.toList()));

        //过滤日期
        for (Map.Entry<String, List<Map>> stringListEntry : hashMap.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                hashMap.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (Map map : stringListEntry.getValue()) {
                map.remove("date");
            }
        }

        Map map = new LinkedHashMap();

        map.put("date",hashMap.keySet());
        map.put("data",hashMap);

        return map;

    }

    //统计每个城市招聘岗位的数量
    @PostMapping("/getPositionCity")
    public Map getPositionCity(@RequestParam(value = "limit", required = false) Integer limit,
                               @RequestParam(value = "city", required = false) ArrayList<String> city){

        //获取所有的城市岗位数量
        List<Map<String, Object>> cityPosition;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            cityPosition = positionNameService.getCityPosition();
        } else {
            cityPosition = positionNameService.getCityPosition(city);
        }

        List<List> list1 = new ArrayList();

        //遍历生成新的list放入list1
        for (Map<String, Object> map : cityPosition) {
            List<Object> list2 = new ArrayList<>();
            list2.add(map.get("YEAR")+"-"+map.get("MONTH"));
            list2.add(map.get("CITY"));
            list2.add(map.get("POSITION_COUNT"));
            list1.add(list2);
        }

        //map根据日期分组
        LinkedHashMap<String, List<List>> hashMap = list1.stream()
                .collect(Collectors.groupingBy(e -> e.get(0).toString(), LinkedHashMap::new, Collectors.toList()));

        //过滤日期
        for (Map.Entry<String, List<List>> stringListEntry : hashMap.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                hashMap.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (List list : stringListEntry.getValue()) {
                list.remove(0);
            }
        }

        Map map = new LinkedHashMap();

        map.put("date",hashMap.keySet());
        map.put("data",hashMap);

        return map;

    }

    //获取岗位名称及招聘数量
    @PostMapping("/getAllPositionCount")
    public List getAllPositionCount(@RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "city", required = false) ArrayList<String> city){

        //获取所有岗位的招聘数量
        List<Map<String,Object>> allPositionCount;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            allPositionCount = positionNameService.getAllPositionCount(limit);
        } else {
            allPositionCount = positionNameService.getAllPositionCount(limit, city);
        }

        List<List> list = new ArrayList();

        for (Map<String, Object> map : allPositionCount) {
            List list1 = new ArrayList<>();
            list1.add(map.get("POSITION"));
            list1.add(map.get("POSITION_COUNT"));
            list.add(list1);
        }

        return list;

    }

    //根据时间查询岗位的招聘数量
    @PostMapping("/getPositionCountByDate")
    public Map<String,Object> getPositionCountByDate(@RequestParam(value = "limit", required = false) Integer limit,
                                                     @RequestParam(value = "city", required = false) ArrayList<String> city){

        //获取所有的岗位
        List<PositionName> allPositionName;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            allPositionName = positionNameService.getAllPositionName();
        } else {
            allPositionName = positionNameService.getAllPositionName(city);
        }

        List<List> list1 = new ArrayList();

        //遍历生成新的list放入list1
        for (PositionName positionName : allPositionName) {
            List list2 = new ArrayList<>();
            list2.add(positionName.getYear()+"-"+positionName.getMonth());
            list2.add(positionName.getPosition());
            list2.add(positionName.getPositionCount());
            list1.add(list2);
        }

        //根据日期分组
        LinkedHashMap<String, List<List>> hashMap = list1.stream()
                .collect(Collectors.groupingBy(e -> e.get(0).toString(), LinkedHashMap::new, Collectors.toList()));

        //过滤日期,限制返回个数
        for (Map.Entry<String, List<List>> stringListEntry : hashMap.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                hashMap.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (List list : stringListEntry.getValue()) {
                list.remove(0);
            }
        }

        Map map = new LinkedHashMap();

        map.put("date",hashMap.keySet());

        map.put("data",hashMap);

        return map;

    }

    //统计某一个岗位招聘城市的分布情况
    @PostMapping("/getPositionCityByPosition")
    public List<Map<String,Object>> getPositionCityByPosition(@RequestParam(value = "position",required = false) String position,
                                                              @RequestParam(value = "city", required = false) ArrayList<String> city,
                                                              @RequestParam(value = "limit", required = false) Integer limit){

        if (position == null){
            position = positionNameService.getHighestPositionCount(null,null).getPosition();
        }

        List<Map<String, Object>> positionCityByPosition;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionCityByPosition = positionNameService.getPositionCityByPosition(position);
        } else {
            positionCityByPosition = positionNameService.getPositionCityByPosition(position,city);
        }

        List<Map<String,Object>> list = new ArrayList<>();

        for (Map<String, Object> map : positionCityByPosition) {
            Map<String,Object> map1 = new LinkedHashMap<>();
            map1.put("name",map.get("CITY"));
            map1.put("value",map.get("POSITION_COUNT"));
            list.add(map1);
        }

        List<Map<String, Object>> subList;

        if (limit != null && limit < list.size()) {
            subList = list.subList(0, limit);
        } else {
            return list;
        }

        Long sum = 0L;
        for (int i = limit; i < list.size(); i++) {
            sum += (Long) list.get(i).get("value");
        }
        Map<String,Object> newMap = new LinkedHashMap<>();
        newMap.put("name","其他");
        newMap.put("value",sum);
        subList.add(newMap);

        return subList;

    }

    //获取所有的岗位名称
    @PostMapping("/getAllPosition")
    public Map<String,Object> getAllPosition(){

        List<String> allPosition = positionNameService.getAllPosition();

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("data",allPosition);

        return map;

    }



}
