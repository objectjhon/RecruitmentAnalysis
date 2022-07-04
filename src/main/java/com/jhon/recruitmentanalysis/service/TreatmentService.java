package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.Treatment;

import java.util.List;

public interface TreatmentService {

    List<Treatment> getTreatment(String query);

    Treatment getTreatmentById(Integer id);

    Integer addTreatment(Treatment treatment);

    Integer deleteTreatment(Integer id);

    Integer updateTreatment(Integer id,String treatment);

}
