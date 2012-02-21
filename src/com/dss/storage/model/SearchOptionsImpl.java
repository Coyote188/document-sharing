package com.dss.storage.model;

import java.util.ArrayList;
import java.util.List;

import com.dss.storage.bean.SearchOptions;
import com.dss.storage.bean.Searcher;

public class SearchOptionsImpl implements SearchOptions<Document>
{
    private List<Searcher<Document>> searchOptions;

    public SearchOptionsImpl() {
        searchOptions = new ArrayList<Searcher<Document>>();
        searchOptions.add(new SearchByTitle());
        searchOptions.add(new SearchByTags());
        searchOptions.add(new SearchByDescription());
    }

    public int count()
    {
        return searchOptions.size();
    }

    @Override
    public List getSearcher()
    {
        return searchOptions;
    }

    public Searcher<Document> getSearcher(int index)
    {
        if (index >= searchOptions.size())
            throw new ArrayIndexOutOfBoundsException();
        else
            return searchOptions.get(index);
    }

}
