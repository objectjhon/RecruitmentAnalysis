package com.jhon.recruitmentanalysis;

import com.jhon.recruitmentanalysis.mapper.PositionCityKeyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RecruitmentAnalysisApplicationTests {

    @Resource
    private PositionCityKeyMapper positionCityKeyMapper;

    @Test
    void contextLoads() {

        //获取所有岗位关键词以及出现次数
        String allKeys[] = positionCityKeyMapper.getAllKey();
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

        System.out.println(allKeysMap);

        //获取某个岗位的所有关键词以及出现次数
        String Keys[] = positionCityKeyMapper.getKey("大数据开发工程师");
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

        //计算占比存入map
        Map<String,Object> percentMap = new HashMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : keysMap.entrySet()) {
            String key = stringIntegerEntry.getKey();
            int allCount = allKeysMap.get(key);
            int count = stringIntegerEntry.getValue();
            double percent = count * 1.0 / allCount;
            DecimalFormat df = new DecimalFormat("0.0000");
            percentMap.put(key,df.format(percent));
        }

        System.out.println(percentMap);

    }

}
