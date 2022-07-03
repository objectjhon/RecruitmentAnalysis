package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.DataSource;
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

//    @GetMapping("/admin/getDataSources")
//    public R getDataSources(@RequestParam(value = "query",required = false) String query,
//                            @RequestParam("pageNum") int pageNum,
//                            @RequestParam("pageSize") int pageSize){
//
//        PageHelper.startPage(pageNum,pageSize);
//
//        List<DataSource> dataSourceList = dataSourceService.getAllDataSource(query);
//
//        PageInfo<DataSource> pageInfo = new PageInfo<>(dataSourceList);
//
//        if(pageInfo != null){
//            Map<String,Object> map=new HashMap<>();
//            map.put("dataSourceList",pageInfo);
//            return R.ok().message("查询成功").data(map);
//        }else {
//            return R.error().message("当前没有数据源");
//        }
//
//    }
//
//    @GetMapping("/admin/addDataSource")
//    public R addDataSource(@RequestParam("dataUrl") String dataUrl,
//                           @RequestParam("tableName") String tableName,
//                           @RequestParam("username") String username,
//                           @RequestParam("password") String password,
//                           @RequestParam("driver") String driver,
//                           @RequestParam("dataType") String dataType,
//                           @RequestParam("fileType") String fileType,
//                           @RequestParam("status") Integer status){
//
//        DataSource dataSource = new DataSource();
//        dataSource.setDataUrl(dataUrl);
//        dataSource.setTableName(tableName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriver(driver);
//        dataSource.setDataType(dataType);
//        dataSource.setFileType(fileType);
//        dataSource.setStatus(status);
//        Integer i = dataSourceService.addDataSource(dataSource);
//
//        if (i > 0) {
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("dataSource",dataSource);
//            return R.ok().message("添加数据源成功").data(map);
//        }
//
//        return R.error().message("添加数据源失败");
//
//    }
//
//    @GetMapping("/admin/getDataSourceById")
//    public R getDataSourceById(@RequestParam("id") Integer id){
//
//        DataSource dataSource = dataSourceService.getDataSourceById(id);
//
//        if (dataSource != null) {
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("dataSource",dataSource);
//            return R.ok().message("查询成功").data(map);
//        }
//
//        return R.error().message("未查询到该数据源");
//
//    }
//
//    @GetMapping("/admin/updateDataSource")
//    public R updateDataSource(@RequestParam("id") Integer id,
//                              @RequestParam("dataUrl") String dataUrl,
//                              @RequestParam("tableName") String tableName,
//                              @RequestParam("username") String username,
//                              @RequestParam("password") String password,
//                              @RequestParam("driver") String driver,
//                              @RequestParam("dataType") String dataType,
//                              @RequestParam("fileType") String fileType){
//
//        DataSource dataSource = new DataSource();
//        dataSource.setId(id);
//        dataSource.setDataUrl(dataUrl);
//        dataSource.setTableName(tableName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriver(driver);
//        dataSource.setDataType(dataType);
//        dataSource.setFileType(fileType);
//
//        Integer i = dataSourceService.updateDataSource(dataSource);
//
//        if (i > 0) {
//            return R.ok().message("修改数据源成功");
//        }
//
//        return R.error().message("修改数据源失败");
//
//    }
//
//    @GetMapping("/admin/updateDataStatus")
//    public R updateDataStatus(@RequestParam("id") Integer id,
//                              @RequestParam("status") Integer status){
//
//        Integer i = dataSourceService.updateDataStatus(id,status);
//
//        if (i > 0) {
//            return R.ok().message("修改数据源状态成功");
//        }
//
//        return R.error().message("修改数据源状态失败");
//
//    }
//
//    @GetMapping("/admin/deleteDataSource")
//    public R deleteDataSource(@RequestParam("id") Integer id){
//
//        Integer i = dataSourceService.deleteDataSource(id);
//
//        if (i > 0) {
//            return R.ok().message("删除数据源成功");
//        }
//
//        return R.error().message("删除数据源失败");
//
//    }

}
