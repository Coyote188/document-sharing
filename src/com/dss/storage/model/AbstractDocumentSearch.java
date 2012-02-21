package com.dss.storage.model;

import java.util.List;

import javax.annotation.Resource;

import com.dss.storage.bean.Searcher;
import com.dss.storage.repository.DocumentSearchRepository;

public abstract class AbstractDocumentSearch
        implements Searcher<Document>
{
    private String key;
    @Resource
    protected DocumentSearchRepository repository;
    @Override
    public String getKeyWord()
    {
        return key;
    }

    @Override
    public void setKeyWord(String key)
    {
        this.key = key;
    }
    
}
