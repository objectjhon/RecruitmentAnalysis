package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.TreatmentMapper;
import com.jhon.recruitmentanalysis.pojo.Treatment;
import com.jhon.recruitmentanalysis.service.TreatmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Resource
    TreatmentMapper treatmentMapper;

    @Override
    public List<Treatment> getTreatment(String query) {
        return treatmentMapper.getTreatment(query);
    }

    @Override
    public Treatment getTreatmentById(Integer id) {
        return treatmentMapper.getTreatmentById(id);
    }

    @Override
    public Integer addTreatment(Treatment treatment) {
        return treatmentMapper.addTreatment(treatment);
    }

    @Override
    public Integer deleteTreatment(Integer id) {
        return treatmentMapper.deleteTreatment(id);
    }

    @Override
    public Integer updateTreatment(Integer id, String treatment) {
        return treatmentMapper.updateTreatment(id,treatment);
    }
}
