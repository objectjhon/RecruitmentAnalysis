package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.KeyWords;

import java.util.List;

public interface KeyWordsService {

    List<KeyWords> getAllKeyWords(String query);

    Integer addKeyWord(KeyWords keyWords);

    KeyWords getKeyWordById(Integer id);

    Integer updateKeyWord(Integer id,String keyvalue,String replacekeyvalue);

    Integer deleteKeyWord(Integer id);

}
