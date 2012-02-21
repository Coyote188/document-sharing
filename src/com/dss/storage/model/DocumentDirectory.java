package com.dss.storage.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import com.dss.account.model.Member;
import com.dss.core.IRepository;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.util.hibernate.HibernateEntity;

@Configurable
@Scope("prototype")
public class DocumentDirectory
        extends DirectoryStructure
        implements DocumentDirectoryBean
{
    private Member member;

    public DocumentDirectory() {
    }

    @Override
    @Resource
    protected void setRepository(@Qualifier("documentDirectoryRepository") IRepository<? super HibernateEntity> repository)
    {
        super.setRepository(repository);
    }

    @Override
    public List getDocuments()
    {
        // TODO get all documents for this directory
        return null;
    }

    @Override
    public DocumentDirectory getParent()
    {
        return (DocumentDirectory) super.getParent();
    }

    public void setParent(DocumentDirectory parent)
    {
        super.setParent(parent);
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public Member getMember()
    {
        return member;
    }

}
