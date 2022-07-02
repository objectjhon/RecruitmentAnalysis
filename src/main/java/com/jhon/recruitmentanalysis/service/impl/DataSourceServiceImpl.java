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

}
