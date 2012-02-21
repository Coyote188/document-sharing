package com.dss.storage.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class SearchByTags
        extends AbstractDocumentSearch
{

    @Override
    public String getName()
    {
        return "±Í«©≤È—Ø";
    }

    @Override
    public List<Document> search()
    {
        return repository.findByTags(getKeyWord());
    }

}
