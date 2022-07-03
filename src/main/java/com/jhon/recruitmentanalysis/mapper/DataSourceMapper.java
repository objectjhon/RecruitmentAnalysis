package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataSourceMapper {

    List<DataSource> getAllDataSource(@Param("query") String query);

    Integer addDataSource(DataSource dataSource);

    DataSource getDataSourceById(@Param("id") Integer id);

    Integer updateDataSource(DataSource dataSource);

    Integer updateDataStatus(Integer id,Integer status);

    Integer deleteDataSource(Integer id);

}
