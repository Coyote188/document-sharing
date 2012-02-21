package com.dss.storage.bean;

import java.util.List;


public interface SearchOptions<T>
{
    int count();
    List<Searcher<T>> getSearcher();
    Searcher<T> getSearcher(int index);
}