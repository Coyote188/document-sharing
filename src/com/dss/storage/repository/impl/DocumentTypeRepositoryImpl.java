package com.dss.storage.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.model.DocumentDirectory;
import com.dss.storage.model.DocumentType;
import com.dss.storage.repository.DocumentTypeRepository;
import com.dss.util.hibernate.HibernateRepository;
import com.dss.util.log.LogUtil;

@Repository("documentTypeRepository")
public class DocumentTypeRepositoryImpl
        extends HibernateRepository<DocumentType>
        implements DocumentTypeRepository
{

    @Autowired
    public DocumentTypeRepositoryImpl(SessionFactory factory) {
        super(factory);
    }

    public DocumentType newType()
    {
        return new DocumentType();
    }

    @Override
    public DocumentType getRootDocumentType()
    {
        List directoryList = getHibernateTemplate().find(
                "from DocumentType p where p.parent is null");

        LogUtil.debug(getClass(), "found directory of root: " + directoryList.size());
        if (directoryList.size() != 0)
            return (DocumentType) directoryList.get(0);
        else
            return null;
    }

}
