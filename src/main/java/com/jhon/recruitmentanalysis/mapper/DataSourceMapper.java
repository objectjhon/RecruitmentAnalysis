package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.DataSource;
import com.jhon.recruitmentanalysis.pojo.KeyWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataSourceMapper {

    List<DataSource> getAllDataSource(@Param("query") String query);

}
