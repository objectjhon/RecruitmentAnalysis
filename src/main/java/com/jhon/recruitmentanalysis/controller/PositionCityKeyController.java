package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.*;
import com.jhon.recruitmentanalysis.service.PositionCityKeyService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class PositionCityKeyController {
    
    @Resource
    private PositionCityKeyService positionCityKeyService;
    
    @GetMapping("/findAllPositionCityKey")
    public R findAllPositionCityKey(@RequestParam(value = "limit", required = false) Integer limit){
        List<PositionCityKey> allPositionCityKey = positionCityKeyService.findAllPositionCityKey(limit);

        List<Object> mapList = new ArrayList<>();

        for (PositionCityKey positionCityKey : allPositionCityKey) {
            String str[] = {positionCityKey.getCity(),positionCityKey.getPositionCount()+""};
            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allPositionCityKeyList",mapList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/findAllPositionCityKey")
    public R postFindAllPositionCityKey(@RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "city",required = false) ArrayList<String> city){

        List<PositionCityKey> allPositionCityKey;

        System.out.println("-----"+limit);

        if (city != null){

            allPositionCityKey = positionCityKeyService.postFindAllPositionCityKey(limit, city);

        } else {

            allPositionCityKey = positionCityKeyService.findAllPositionCityKey(limit);

        }

        List<Object> mapList = new ArrayList<>();

        for (PositionCityKey positionCityKey : allPositionCityKey) {
            String str[] = {positionCityKey.getCity(),positionCityKey.getPositionCount()+""};
            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allPositionCityKeyList",mapList);

        return R.ok().message("查询成功").data(map);
    }

    @GetMapping("/getAllPositionCityKey")
    public R getAllPositionCityKey(){
        List<PositionCityKeyVo> allPositionCityKeyVo = positionCityKeyService.getAllPositionCityKey();

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("allPositionCityKeyVoList",allPositionCityKeyVo);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/getAllPositionCityKey")
    public R postGetAllPositionCityKey(@RequestParam(value = "city",required = false) ArrayList<String> city){
        List<PositionCityKeyVo> allPositionCityKeyVo;

        if (city != null){

            allPositionCityKeyVo = positionCityKeyService.postGetAllPositionCityKey(city);

        } else {

            allPositionCityKeyVo = positionCityKeyService.getAllPositionCityKey();

        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("allPositionCityKeyVoList",allPositionCityKeyVo);

        return R.ok().message("查询成功").data(map);
    }

    @GetMapping("/getAllPositionCity")
    public R getAllPositionCity(@RequestParam(value = "limit", required = false) Integer limit){
        List<PositionCityKeyVo> allPositionCity = positionCityKeyService.getAllPositionCity(limit);

        List<Object> mapList = new ArrayList<>();

        for (PositionCityKeyVo positionCityKeyVo : allPositionCity) {
            String str[] = {positionCityKeyVo.getName(),positionCityKeyVo.getValue()+""};
            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allPositionCityList",mapList);

        return R.ok().message("查询成功").data(map);
    }

    @PostMapping("/getAllPositionCity")
    public R postGetAllPositionCity(@RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "city",required = false) ArrayList<String> city){

        List<PositionCityKeyVo> allPositionCity;

        if (city != null){

            allPositionCity = positionCityKeyService.postGetAllPositionCity(limit, city);

        } else {

            allPositionCity = positionCityKeyService.getAllPositionCity(limit);

        }

        List<Object> mapList = new ArrayList<>();

        for (PositionCityKeyVo positionCityKeyVo : allPositionCity) {
            String str[] = {positionCityKeyVo.getName(),positionCityKeyVo.getValue()+""};
            mapList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("name","个");

        map.put("allPositionCityList",mapList);

        return R.ok().message("查询成功").data(map);
    }

    @GetMapping("/getPercentMap")
    public R getPercentMap(@RequestParam(value = "position", required = false) String position){

        Map<String,Integer> allKeysMap = positionCityKeyService.getAllKey();
        Map<String,Integer> keysMap = positionCityKeyService.getKey(position);

        Map<String,Object> percentMap = new HashMap<>();

        List<PositionCityKeyPo> positionCityKeyVoList = new ArrayList<>();

        for (Map.Entry<String, Integer> stringIntegerEntry : keysMap.entrySet()) {
            String key = stringIntegerEntry.getKey();
            int allCount = allKeysMap.get(key);
            int count = stringIntegerEntry.getValue();
            double percent = count * 1.0 / allCount;
            DecimalFormat df = new DecimalFormat("0.0000");

            PositionCityKeyPo positionCityKeyPo = new PositionCityKeyPo();
            positionCityKeyPo.setName(key);
            positionCityKeyPo.setValue(df.format(percent));
            positionCityKeyVoList.add(positionCityKeyPo);

        }

        percentMap.put("allKeyPercent",positionCityKeyVoList);

        return R.ok().message("查询成功").data(percentMap);
    }

    // 根据城市查询关键字及个数
    @PostMapping("/getKeyAndVal")
    public R getAllKey(@RequestParam(value = "city",required = false) ArrayList<String> city){

        Map<String,Integer> allKeysMap;

        List<Object> keysList = new ArrayList<>();

        if (city != null) {

            allKeysMap = positionCityKeyService.getAllKey(city);

        } else {

            allKeysMap = positionCityKeyService.getAllKey();

        }

        for (Map.Entry<String, Integer> stringIntegerEntry : allKeysMap.entrySet()) {
            PositionCityKeyVo positionCityKeyVo = new PositionCityKeyVo();
            positionCityKeyVo.setName(stringIntegerEntry.getKey());
            positionCityKeyVo.setValue(stringIntegerEntry.getValue());
            keysList.add(positionCityKeyVo);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("keysList", keysList);

        return R.ok().message("查询成功").data(map);

    }

    // 当前区域招聘岗位数量最多的城市
    @PostMapping("/getHighestCityCount")
    public R getHighestCityCount(@RequestParam(value = "city",required = false) ArrayList<String> city){

        CityCount highestCityCount;

        if (city != null) {

            highestCityCount = positionCityKeyService.getHighestCityCount(city);

        } else {

            highestCityCount = positionCityKeyService.getHighestCityCount();

        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("highestCityCount",highestCityCount);

        return R.ok().message("查询成功").data(map);
    }

    // 查询所有岗位的名称
    @PostMapping("/getAllPosition")
    public R getAllPosition(@RequestParam(value = "limit", required = false) Integer limit,
                            @RequestParam(value = "city",required = false) ArrayList<String> city){

        String[] positions;

        if (city != null){

            positions = positionCityKeyService.getAllPosition(limit,city);

        } else {

            positions = positionCityKeyService.getAllPosition(limit);

        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("allPosition",positions);

        return R.ok().message("查询成功").data(map);
    }

    // 查询岗位专业术语提到的次数
    @PostMapping("/getKeyByPosition")
    public R getKeyByPosition(@RequestParam(value = "position",required = false) String position,
                              @RequestParam(value = "city",required = false) ArrayList<String> city){

        Map<String,Object> map = new LinkedHashMap<>();

        //根据岗位查询关键字
        Map<String,Integer> keyByPositionMap;

        if (city != null){

            keyByPositionMap = positionCityKeyService.getKeyByPosition(position, city);

        } else {

            keyByPositionMap = positionCityKeyService.getKeyByPosition(position);

        }

        int max = 0;

        Object[] objects  = keyByPositionMap.values().stream().sorted().toArray();
        max = (int) objects[objects.length-1];

        map.put("max",max);
        map.put("nameList",keyByPositionMap.keySet());
        Map<String,Object> newMap = new LinkedHashMap<>();
        newMap.put("name",position);
        newMap.put("value",keyByPositionMap.values());
        map.put("data",newMap);

        return R.ok().message("查询成功").data(map);

    }
    
}
