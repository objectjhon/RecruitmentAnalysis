package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.DataSource;

import java.util.List;

public interface DataSourceService {

    List<DataSource> getAllDataSource(String query);

    Integer addDataSource(DataSource dataSource);

    DataSource getDataSourceById(Integer id);

    Integer updateDataSource(DataSource dataSource);

    Integer updateDataStatus(Integer id,Integer status);

    Integer deleteDataSource(Integer id);

}
