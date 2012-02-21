package com.dss.storage.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class SearchByTitle
        extends AbstractDocumentSearch
{

    @Override
    public String getName()
    {       
        return "±ÍÃ‚≤È—Ø";
    }

    @Override
    public List<Document> search()
    {
        return repository.findByDocumentName(getKeyWord());
    }

}
