package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.DataSource;
import com.jhon.recruitmentanalysis.service.DataSourceService;
import com.jhon.recruitmentanalysis.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DataSourceController {

    @Resource
    DataSourceService dataSourceService;

    @GetMapping("/admin/getDataSources")
    public R getDataSources(@RequestParam(value = "query",required = false) String query,
                            @RequestParam("pageNum") int pageNum,
                            @RequestParam("pageSize") int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<DataSource> dataSourceList = dataSourceService.getAllDataSource(query);

        PageInfo<DataSource> pageInfo = new PageInfo<>(dataSourceList);

        if(pageInfo != null){
            Map<String,Object> map=new HashMap<>();
            map.put("keyWordsList",pageInfo);
            return R.ok().message("查询成功").data(map);
        }else {
            return R.error().message("当前没有数据源");
        }

    }

}
