package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionKeyMapper;
import com.jhon.recruitmentanalysis.service.PositionKeyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PositionKeyServiceImpl implements PositionKeyService {

    @Resource
    PositionKeyMapper positionKeyMapper;

    @Override
    public Map<String, Integer> getAllKeyValue(List<String> city) {

        //查询全部的关键字
        String[] allKey;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            allKey = positionKeyMapper.getAllKey();
        } else {
            allKey = positionKeyMapper.getAllKeyByCity(city);
        }

        List<String> allKeyValList = new ArrayList<>();

        //将每个关键字单独提取到list
        for (String s : allKey) {
            for (String s1 : s.split("\\|")) {
                allKeyValList.add(s1);
            }
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        //将每个关键字的个数计算出来放入map
        for (String s : allKeyValList) {
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }

        return map;
    }

    @Override
    public Map<String, Integer> getPositionKeyValue(String position) {

        //根据岗位查询关键字
        String[] positionKey = positionKeyMapper.getPositionKey(position);

        List<String> positionKeyValList = new ArrayList<>();

        //将每个关键字单独提取到list
        for (String s : positionKey) {
            for (String s1 : s.split("\\|")) {
                positionKeyValList.add(s1);
            }
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        //将每个关键字的个数计算出来放入map
        for (String s : positionKeyValList) {
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }

        return map;

    }

    @Override
    public Map<String, Integer> getPositionKeyValue(String position, List<String> city) {

        //根据岗位查询关键字
        String[] positionKey = positionKeyMapper.getPositionKeyByCity(position,city);

        List<String> positionKeyValList = new ArrayList<>();

        //将每个关键字单独提取到list
        for (String s : positionKey) {
            for (String s1 : s.split("\\|")) {
                positionKeyValList.add(s1);
            }
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        //将每个关键字的个数计算出来放入map
        for (String s : positionKeyValList) {
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }

        return map;

    }

    @Override
    public Map<String, Integer> getKeyBySalaryPosition(String position, BigDecimal salaryMin, BigDecimal salaryMax, List<String> city) {

        String[] keyBySalaryPosition;

        //判断城市参数是否为空
        if (city == null || city.size() == 0) {
            keyBySalaryPosition = positionKeyMapper.getKeyBySalaryPosition(position,salaryMin,salaryMax);
        } else {
            keyBySalaryPosition = positionKeyMapper.getKeyBySalaryPositionCity(position,salaryMin,salaryMax,city);
        }

        List<String> positionKeyValList = new ArrayList<>();

        //将每个关键字单独提取到list
        for (String s : keyBySalaryPosition) {
            for (String s1 : s.split("\\|")) {
                positionKeyValList.add(s1);
            }
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        //将每个关键字的个数计算出来放入map
        for (String s : positionKeyValList) {
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }

        return map;

    }

}
