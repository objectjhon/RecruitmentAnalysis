package com.jhon.recruitmentanalysis.controller;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePositionCount;
import com.jhon.recruitmentanalysis.pojo.PositionCityKey;
import com.jhon.recruitmentanalysis.pojo.PositionCityKeyPo;
import com.jhon.recruitmentanalysis.pojo.PositionCityKeyVo;
import com.jhon.recruitmentanalysis.service.PositionCityKeyService;
import com.jhon.recruitmentanalysis.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

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
            String str[] = {stringIntegerEntry.getKey(),stringIntegerEntry.getValue()+""};
            keysList.add(str);
        }

        Map<String,Object> map = new LinkedHashMap<>();

        map.put("keysList", keysList);

        return R.ok().message("查询成功").data(map);

    }
    
}
