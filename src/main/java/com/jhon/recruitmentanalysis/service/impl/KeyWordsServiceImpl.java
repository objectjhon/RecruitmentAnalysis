package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.KeyWordsMapper;
import com.jhon.recruitmentanalysis.pojo.KeyWords;
import com.jhon.recruitmentanalysis.service.KeyWordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeyWordsServiceImpl implements KeyWordsService {

    @Resource
    KeyWordsMapper keyWordsMapper;

    @Override
    public List<KeyWords> getAllKeyWords(String query) {
        return keyWordsMapper.getAllKeyWords(query);
    }

    @Override
    public Integer addKeyWord(KeyWords keyWords) {
        return keyWordsMapper.addKeyWord(keyWords);
    }

    @Override
    public KeyWords getKeyWordById(Integer id) {
        return keyWordsMapper.getKeyWordById(id);
    }

    @Override
    public Integer updateKeyWord(Integer id, String keyValue, String replaceKeyValue) {
        return keyWordsMapper.updateKeyWord(id,keyValue,replaceKeyValue);
    }

    @Override
    public Integer updateStatus(Integer id, Integer status) {
        return keyWordsMapper.updateStatus(id,status);
    }

    @Override
    public Integer deleteKeyWord(Integer id) {
        return keyWordsMapper.deleteKeyWord(id);
    }

}
