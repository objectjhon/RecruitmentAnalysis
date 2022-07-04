package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.PositionValue;
import com.jhon.recruitmentanalysis.service.PositionValueService;
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
public class PositionValueController {

    @Resource
    PositionValueService positionValueService;

    @GetMapping("/admin/getPositionValue")
    public R getPositionValue(@RequestParam(value = "query",required = false) String query,
                              @RequestParam("pageNum") int pageNum,
                              @RequestParam("pageSize") int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<PositionValue> positionValues = positionValueService.getAllPositionValue(query);

        PageInfo<PositionValue> pageInfo = new PageInfo<>(positionValues);

        if(pageInfo != null){
            Map<String,Object> map=new HashMap<>();
            map.put("positionValueList",pageInfo);
            return R.ok().message("查询成功").data(map);
        }else {
            return R.error().message("当前没有职位");
        }

    }

    @GetMapping("/admin/addPositionValue")
    public R addPositionValue(@RequestParam("positionValue") String positionValue,
                              @RequestParam("replacePositionValue") String replacePositionValue,
                              @RequestParam("status") Integer status){

        PositionValue positionValue1 = new PositionValue();
        positionValue1.setPositionValue(positionValue);
        positionValue1.setReplacePositionValue(replacePositionValue);
        positionValue1.setStatus(status);
        Integer i = positionValueService.addPositionValue(positionValue1);

        if (i > 0) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("positionValue",positionValue1);
            return R.ok().message("添加职位成功").data(map);
        }

        return R.error().message("添加职位失败");

    }

    @GetMapping("/admin/getPositionValueById")
    public R getPositionValueById(@RequestParam("id") Integer id){

        PositionValue positionValue = positionValueService.getPositionValueById(id);

        if (positionValue != null) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("positionValue",positionValue);
            return R.ok().message("查询成功").data(map);
        }

        return R.error().message("未查询到该职位");

    }

    @GetMapping("/admin/updatePositionValue")
    public R updatePositionValue(@RequestParam("id") Integer id,
                                 @RequestParam("positionValue") String positionValue,
                                 @RequestParam("replacePositionValue") String replacePositionValue){

        PositionValue positionValue1 = new PositionValue();
        positionValue1.setId(id);
        positionValue1.setPositionValue(positionValue);
        positionValue1.setReplacePositionValue(replacePositionValue);

        Integer i = positionValueService.updatePositionValue(positionValue1);

        if (i > 0) {
            return R.ok().message("修改职位成功");
        }

        return R.error().message("修改职位失败");

    }

    @GetMapping("/admin/updatePositionStatus")
    public R updatePositionStatus(@RequestParam("id") Integer id,
                                  @RequestParam("status") Integer status){

        Integer i = positionValueService.updatePositionStatus(id,status);

        if (i > 0) {
            return R.ok().message("修改职位状态成功");
        }

        return R.error().message("修改职位状态失败");

    }

    @GetMapping("/admin/deletePositionValue")
    public R deletePositionValue(@RequestParam("id") Integer id){

        Integer i = positionValueService.deletePositionValue(id);

        if (i > 0) {
            return R.ok().message("删除职位成功");
        }

        return R.error().message("删除职位失败");

    }

}
