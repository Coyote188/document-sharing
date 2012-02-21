package com.dss.storage.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class SearchByDescription extends AbstractDocumentSearch
{

    @Override
    public String getName()
    {
        return "ºÚΩÈ≤È—Ø";
    }

    @Override
    public List<Document> search()
    {
        // TODO repository not yet implement
        return null;
    }

}
