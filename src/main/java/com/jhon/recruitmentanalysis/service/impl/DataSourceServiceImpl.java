package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.DataSourceMapper;
import com.jhon.recruitmentanalysis.pojo.DataSource;
import com.jhon.recruitmentanalysis.service.DataSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    DataSourceMapper dataSourceMapper;

    @Override
    public List<DataSource> getAllDataSource(String query) {
        return dataSourceMapper.getAllDataSource(query);
    }

    @Override
    public Integer addDataSource(DataSource dataSource) {
        return dataSourceMapper.addDataSource(dataSource);
    }

    @Override
    public DataSource getDataSourceById(Integer id) {
        return dataSourceMapper.getDataSourceById(id);
    }

    @Override
    public Integer updateDataSource(DataSource dataSource) {
        return dataSourceMapper.updateDataSource(dataSource);
    }

    @Override
    public Integer updateDataStatus(Integer id, Integer status) {
        return dataSourceMapper.updateDataStatus(id,status);
    }

    @Override
    public Integer deleteDataSource(Integer id) {
        return dataSourceMapper.deleteDataSource(id);
    }

}
