package com.dss.storage.bean;

import java.util.List;

public interface Searcher<T>
{
    String getName();
    String getKeyWord();
    void setKeyWord(String key);
    List<T> search();
}
