package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionCityKeyMapper;
import com.jhon.recruitmentanalysis.pojo.CityCount;
import com.jhon.recruitmentanalysis.pojo.PositionCityKey;
import com.jhon.recruitmentanalysis.pojo.PositionCityKeyVo;
import com.jhon.recruitmentanalysis.service.PositionCityKeyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionCityKeyServiceImpl implements PositionCityKeyService {

    @Resource
    private PositionCityKeyMapper positionCityKeyMapper;

    @Override
    public List<PositionCityKey> findAllPositionCityKey(Integer limit) {
        return positionCityKeyMapper.findAllPositionCityKey(limit);
    }

    @Override
    public List<PositionCityKey> postFindAllPositionCityKey(Integer limit, List<String> city) {
        return positionCityKeyMapper.postFindAllPositionCityKey(limit, city);
    }

    @Override
    public List<PositionCityKeyVo> getAllPositionCityKey() {
        return positionCityKeyMapper.getAllPositionCityKey();
    }

    @Override
    public List<PositionCityKeyVo> postGetAllPositionCityKey(List<String> city) {
        return positionCityKeyMapper.postGetAllPositionCityKey(city);
    }

    @Override
    public List<PositionCityKeyVo> getAllPositionCity(Integer limit) {
        return positionCityKeyMapper.getAllPositionCity(limit);
    }

    @Override
    public List<PositionCityKeyVo> postGetAllPositionCity(Integer limit, List<String> city) {
        return positionCityKeyMapper.postGetAllPositionCity(limit, city);
    }

    @Override
    public Map<String,Integer> getKey(String position) {
        String[] Keys = positionCityKeyMapper.getKey(position);
        List<String> keysList = new ArrayList<>();
        for (String key : Keys) {
            for (String s : key.split(" ")) {
                if (!s.equals("")){
                    keysList.add(s);
                }
            }
        }
        Map<String,Integer> keysMap = new HashMap<>();
        for (String s : keysList) {
            Integer count = keysMap.get(s);
            keysMap.put(s, (count == null) ? 1 : count + 1);
        }
        return keysMap;
    }

    @Override
    public Map<String,Integer> getAllKey() {
        String[] allKeys = positionCityKeyMapper.getAllKey();
        List<String> allKeysList = new ArrayList<>();
        for (String allKey : allKeys) {
            for (String s : allKey.split(" ")) {
                if (!s.equals("")){
                    allKeysList.add(s);
                }
            }
        }
        Map<String,Integer> allKeysMap = new HashMap<>();
        for (String s : allKeysList) {
            Integer count = allKeysMap.get(s);
            allKeysMap.put(s, (count == null) ? 1 : count + 1);
        }
        return allKeysMap;
    }

    @Override
    public Map<String, Integer> getAllKey(List<String> city) {
        String[] allKeyByCity = positionCityKeyMapper.getAllKeyByCity(city);
        List<String> allKeyValList = new ArrayList<>();
        for (String s : allKeyByCity) {
            for (String s1 : s.split(" ")) {
                if (!s1.equals("")){
                    allKeyValList.add(s1);
                }
            }
        }
        Map<String,Integer> allKeyValMap = new HashMap<>();
        for (String s : allKeyValList) {
            Integer count = allKeyValMap.get(s);
            allKeyValMap.put(s, (count == null) ? 1 : count + 1);
        }
        return allKeyValMap;
    }

    @Override
    public CityCount getHighestCityCount() {
        return positionCityKeyMapper.getHighestCityCount();
    }

    @Override
    public CityCount getHighestCityCount(List<String> city) {
        return positionCityKeyMapper.postGetHighestCityCount(city);
    }

}
