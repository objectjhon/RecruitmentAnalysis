package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.Treatment;
import com.jhon.recruitmentanalysis.service.TreatmentService;
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
public class TreatmentController {

    @Resource
    TreatmentService treatmentService;

    @GetMapping("/admin/getTreatment")
    public R getTreatment(@RequestParam(value = "query",required = false) String query,
                          @RequestParam("pageNum") int pageNum,
                          @RequestParam("pageSize") int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Treatment> treatments = treatmentService.getTreatment(query);

        PageInfo<Treatment> pageInfo = new PageInfo<>(treatments);

        if(pageInfo != null){
            Map<String,Object> map=new HashMap<>();
            map.put("treatmentList",pageInfo);
            return R.ok().message("查询成功").data(map);
        }else {
            return R.error().message("当前没有福利");
        }

    }

    @GetMapping("/admin/getTreatmentById")
    public R getTreatmentById(@RequestParam("id") Integer id){

        Treatment treatment = treatmentService.getTreatmentById(id);

        if (treatment != null) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("treatment",treatment);
            return R.ok().message("查询成功").data(map);
        }

        return R.error().message("未查询到该福利");

    }

    @GetMapping("/admin/addTreatment")
    public R addTreatment(@RequestParam("treatment") String treatment){

        Treatment treatment1 = new Treatment();
        treatment1.setTreatment(treatment);

        Integer i = treatmentService.addTreatment(treatment1);

        if (i > 0) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("treatment",treatment);
            return R.ok().message("添加福利成功").data(map);
        }

        return R.error().message("添加福利失败");

    }

    @GetMapping("/admin/deleteTreatment")
    public R deleteTreatment(@RequestParam("id") Integer id){

        Integer i = treatmentService.deleteTreatment(id);

        if (i > 0) {
            return R.ok().message("删除福利成功");
        }

        return R.error().message("删除福利失败");

    }

    @GetMapping("/admin/updateTreatment")
    public R updateTreatment(@RequestParam("id") Integer id,
                             @RequestParam("treatment") String treatment){

        Integer i = treatmentService.updateTreatment(id,treatment);

        if (i > 0) {
            return R.ok().message("修改福利成功");
        }

        return R.error().message("修改福利失败");

    }

}
