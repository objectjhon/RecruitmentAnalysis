package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.Treatment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreatmentMapper {

    List<Treatment> getTreatment(@Param("query") String query);

    Treatment getTreatmentById(@Param("id") Integer id);

    Integer addTreatment(Treatment treatment);

    Integer deleteTreatment(@Param("id") Integer id);

    Integer updateTreatment(@Param("id") Integer id,
                            @Param("treatment") String treatment);

}
