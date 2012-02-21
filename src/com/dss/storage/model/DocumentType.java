package com.dss.storage.model;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.dss.core.IRepository;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.util.hibernate.HibernateEntity;

@Configurable
@Scope("prototype")
public class DocumentType
        extends DirectoryStructure
        implements DocumentTypeBean
{  
    @Override
    @Resource
    protected void setRepository(@Qualifier("documentTypeRepository") IRepository<? super HibernateEntity> repository)
    {
        super.setRepository(repository);
    }

    @Override
    public List<DocumentBean> getDocuments()
    {      
        return null;
    }
    @Override
    public DocumentType getParent()
    {
        return (DocumentType) super.getParent();
    }

    public void setParent(DocumentType parent)
    {
        super.setParent(parent);
    }

}
