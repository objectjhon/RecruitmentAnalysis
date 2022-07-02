package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.KeyWords;
import com.jhon.recruitmentanalysis.pojo.User;
import com.jhon.recruitmentanalysis.service.KeyWordsService;
import com.jhon.recruitmentanalysis.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class KeyWordsController {

    @Resource
    KeyWordsService keyWordsService;

    @GetMapping("/admin/getKeys")
    public R getKeys(@RequestParam(value = "query",required = false) String query,
                     @RequestParam("pageNum") int pageNum,
                     @RequestParam("pageSize") int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<KeyWords> keyWordsList = keyWordsService.getAllKeyWords(query);

        PageInfo<KeyWords> pageInfo = new PageInfo<>(keyWordsList);

        if(pageInfo != null){
            Map<String,Object> map=new HashMap<>();
            map.put("keyWordsList",pageInfo);
            return R.ok().message("查询成功").data(map);
        }else {
            return R.error().message("当前没有关键字");
        }

    }

    @GetMapping("/admin/addKeyWord")
    public R addKeyWord(@RequestParam("keyvalue") String keyvalue,
                        @RequestParam("replacekeyvalue") String replacekeyvalue,
                        @RequestParam("status") Integer status){

        KeyWords keyWords = new KeyWords();
        keyWords.setKeyvalue(keyvalue);
        keyWords.setReplacekeyvalue(replacekeyvalue);
        keyWords.setStatus(status);
        Integer i = keyWordsService.addKeyWord(keyWords);

        if (i > 0) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("keywords",keyWords);
            return R.ok().message("添加关键字成功").data(map);
        }

        return R.error().message("添加关键字失败");

    }

    @GetMapping("/admin/getKeyWordById")
    public R getKeyWordById(@RequestParam("id") Integer id){

        KeyWords keyWords = keyWordsService.getKeyWordById(id);

        if (keyWords != null) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("keyWords",keyWords);
            return R.ok().message("查询成功").data(map);
        }

        return R.error().message("未查询到该关键字");

    }

    @GetMapping("/admin/updateKeyWord")
    public R updateKeyWord(@RequestParam("id") Integer id,
                           @RequestParam("keyvalue") String keyvalue,
                           @RequestParam("replacekeyvalue") String replacekeyvalue){

        Integer i = keyWordsService.updateKeyWord(id,keyvalue,replacekeyvalue);

        if (i > 0) {
            return R.ok().message("修改关键字成功");
        }

        return R.error().message("修改关键字失败");

    }

    @GetMapping("/admin/deleteKeyWord")
    public R deleteKeyWord(@RequestParam("id") Integer id){

        Integer i = keyWordsService.deleteKeyWord(id);

        if (i > 0) {
            return R.ok().message("删除关键字成功");
        }

        return R.error().message("删除关键字失败");

    }

}
