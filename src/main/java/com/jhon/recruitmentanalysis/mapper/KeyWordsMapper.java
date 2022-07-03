package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.KeyWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyWordsMapper {

    List<KeyWords> getAllKeyWords(@Param("query") String query);

    Integer addKeyWord(KeyWords keyWords);

    KeyWords getKeyWordById(@Param("id") Integer id);

    Integer updateKeyWord(@Param("id") Integer id,
                          @Param("keyValue") String keyValue,
                          @Param("replaceKeyValue") String replaceKeyValue);

    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);

    Integer deleteKeyWord(@Param("id") Integer id);

}
