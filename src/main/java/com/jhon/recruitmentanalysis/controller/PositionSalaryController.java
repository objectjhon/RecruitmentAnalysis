package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.PositionSalary;
import com.jhon.recruitmentanalysis.service.PositionSalaryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PositionSalaryController {

    @Resource
    PositionSalaryService positionSalaryService;

    //查询某区域岗位的最高最低平均工资
    @PostMapping("/getAllPositionSalary")
    public Map getAllPositionSalary(@RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "city", required = false) ArrayList<String> city,
                                    @RequestParam(value = "position", required = false) String position){

        //查询所有岗位的最高最低平均工资
        List<PositionSalary> allPositionSalary;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            allPositionSalary = positionSalaryService.getAllPositionSalary(position);
        } else {
            allPositionSalary = positionSalaryService.getAllPositionSalary(position,city);
        }

        List<List> list = new ArrayList<>();

        //遍历生成新的list1放入list
        for (PositionSalary positionSalary : allPositionSalary) {
            List list1 = new ArrayList();
            list1.add(positionSalary.getYear()+"-"+positionSalary.getMonth());
            list1.add(positionSalary.getPosition());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            list1.add(decimalFormat.format(positionSalary.getSalaryMax()));
            list1.add(decimalFormat.format(positionSalary.getSalaryMin()));
            list1.add(decimalFormat.format(positionSalary.getSalaryAvg()));
            list.add(list1);
        }

        //根据日期分组
        Map<String, List<List>> listMap = list.stream()
                .collect(Collectors.groupingBy(e -> e.get(0).toString(),LinkedHashMap::new,Collectors.toList()));

        //过滤日期,限制返回个数
        for (Map.Entry<String, List<List>> stringListEntry : listMap.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                listMap.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (List list1 : stringListEntry.getValue()) {
                list1.remove(0);
            }
        }

        Map map = new LinkedHashMap();

        map.put("date",listMap.keySet());
        List<String> list2
                = Arrays.asList(new String[]{"最高工资","最低工资","平均工资"});
        map.put("name",list2);
        map.put("data",listMap);

        return map;

    }

    //根据薪资范围查询岗位数量
    @PostMapping("/getPositionBySalary")
    public List<Map> getPositionBySalary(@RequestParam(value = "date",required = false) String date,
                                         @RequestParam(value = "city", required = false) ArrayList<String> city){

        String year;
        String month;

        if (date == null){
            year = null;
            month = null;
        } else {
            year = date.split("-")[0];
            month = date.split("-")[1];
        }

        List<Map> list = new ArrayList<>();

        List<Map<String, Object>> positionCount;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionCount = positionSalaryService.getPositionCount(year, month);
        } else {
            positionCount = positionSalaryService.getPositionCount(year, month, city);
        }

        for (Map<String, Object> map : positionCount) {
            Map<String,Object> objectMap = new LinkedHashMap<>();
            objectMap.put("name",map.get("SALARY_SECTION"));
            objectMap.put("value",map.get("POSITION_COUNT"));
            list.add(objectMap);
        }

        List<Map> collect = list.stream()
                .sorted(
                        (h1, h2) -> (Integer.valueOf(h1.get("name").toString().split("-")[0]))
                                .compareTo(Integer.valueOf(h2.get("name").toString().split("-")[0])))
                .collect(Collectors.toList());

        for (Map map : collect) {
            String[] split = map.get("name").toString().split("-");
            map.put("name",Integer.parseInt(split[0])/1000+"k-"+Integer.parseInt(split[1])/1000+"k");
        }

        List<Map> mapList;

        if (collect.size() > 14) {
            mapList = collect.subList(0,14);

            Long sum = 0L;
            for (int i = 14; i < collect.size(); i++) {
                sum += (Long) collect.get(i).get("value");
            }

            Map<String,Object> newMap = new LinkedHashMap<>();
            newMap.put("name",collect.get(14).get("name").toString().split("-")[0]+"+");
            newMap.put("value",sum);
            mapList.add(newMap);
        } else {
            mapList = collect;
        }



        return mapList;

    }

    //根据时间查询岗位的薪资
    @PostMapping("/getPositionSalary")
    public Map<String,Object> getPositionSalary(@RequestParam(value = "limit", required = false) Integer limit,
                                                @RequestParam(value = "city", required = false) ArrayList<String> city){

        List<Map<String, Object>> positionSalary;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            positionSalary = positionSalaryService.getPositionSalary();
        } else {
            positionSalary = positionSalaryService.getPositionSalary(city);
        }

        List<List> groupList = new ArrayList<>();

        for (Map<String, Object> map : positionSalary) {
            List list = new ArrayList();
            list.add(map.get("YEAR")+"-"+map.get("MONTH"));
            list.add(map.get("POSITION"));
            list.add(map.get("SALARY_MAX"));
            groupList.add(list);
        }

        LinkedHashMap<String, List<List>> hashMap = groupList.stream()
                .collect(Collectors.groupingBy(e -> e.get(0).toString(), LinkedHashMap::new, Collectors.toList()));

        //过滤日期,限制返回个数
        for (Map.Entry<String, List<List>> stringListEntry : hashMap.entrySet()) {
            if (limit != null && stringListEntry.getValue().size() > limit){
                hashMap.put(stringListEntry.getKey(),stringListEntry.getValue().subList(0,limit));
            }
            for (List list1 : stringListEntry.getValue()) {
                list1.remove(0);
            }
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("date",hashMap.keySet());

        map.put("data",hashMap);

        return map;

    }

    @PostMapping("/getHighestSalaryPosition")
    public Map getHighestSalaryPosition(@RequestParam(value = "date", required = false) String date,
                                        @RequestParam(value = "city", required = false) ArrayList<String> city){

        Map<String, Object> highestSalaryPosition;

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
            highestSalaryPosition = positionSalaryService.getHighestSalaryPosition(year,month);
        } else {
            highestSalaryPosition = positionSalaryService.getHighestSalaryPositionByCity(year,month,city);
        }

        Map map = new LinkedHashMap();

        map.put(highestSalaryPosition.get("POSITION"),highestSalaryPosition.get("SALARY_MAX"));

        return map;

    }

}
