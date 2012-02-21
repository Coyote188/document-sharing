package com.dss.storage.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.model.Document;
import com.dss.storage.model.DocumentDirectory;
import com.dss.storage.repository.DocumentRepository;
import com.dss.util.hibernate.HibernateRepository;
import com.dss.util.log.LogUtil;

@Repository("documentRepository")
@SuppressWarnings("unchecked")
public class DocumentRepositoryImpl
        extends HibernateRepository<Document>
        implements DocumentRepository
{

    @Autowired
    public DocumentRepositoryImpl(SessionFactory factory) {
        super(factory);
    }

    // @Resource
    // private FileUtil fileUtil;
    /**
     * Prepare all the requirement for the document
     */
    @Override
    public Document newDocument()
    {
        return new Document();
    }

    @Override
    public List<Document> findAllReleasedDocuments()
    {
        String queryString = "from Document";
        return getHibernateTemplate().find(queryString);
    }

    
    @Override
    public boolean isDocumentExist(String md5)
    {
        boolean isExist = false;

        String queryString = "from Document d where d.contentMd5 = ?";
        List result = getHibernateTemplate().find(queryString, md5);
        if (result.size() > 0) {
            isExist = true;
            LogUtil.debug(getClass(), "document is exist");
        } else {
            isExist = false;
            LogUtil.debug(getClass(), "document is not exist");
        }
        return isExist;
    }
    
    

}
