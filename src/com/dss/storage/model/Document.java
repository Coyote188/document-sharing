package com.dss.storage.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;
import com.dss.core.IRepository;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentFormat;
import com.dss.storage.repository.DocumentRepository;
import com.dss.storage.repository.util.FileUtil;
import com.dss.util.hibernate.HibernateEntity;
import com.dss.wanted.model.Solution;

@SuppressWarnings("unused")
@Configurable
@Scope("prototype")
public class Document
        extends HibernateEntity
        implements DocumentBean

{
    private String name;
    private double size;
    private String simpleDescription;
    private String tags;
    private String title;
    private double downloadCash;
    private String contentMd5;
    private String prePicMd5;
    private DocumentFormat format;
    private Date uploadDate;
    private DocumentType type;
    private DocumentDirectoryBean directory;
    private DocumentContent file;
    private Member uploader;

//    @Resource(name = "documentRepository")
//    private IRepository<Document> repo;

    public Document() {
    }
    

    @Override
    @Resource
    protected void setRepository(@Qualifier("documentRepository")IRepository<? super HibernateEntity> repository)
    {
        super.setRepository(repository);
    }

    public DocumentFormat getFormat()
    {
        return this.format;
    }

    public String getName()
    {
        return this.name;
    }

    public double getSize()
    {
        return this.size;
    }

    public void setFormat(DocumentFormat format)
    {
        this.format = format;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public String getSimpleDescription()
    {
        return simpleDescription;
    }

    public String getTags()
    {
        return tags;
    }

    public String getTitle()
    {
        return title;
    }

    public void setSimpleDescription(String desc)
    {
        this.simpleDescription = desc;
    }

    public void setTags(String tags)
    {
        this.tags = tags;

    }

    public void setTitle(String title)
    {
        this.title = title;

    }

    public DocumentType getType()
    {
        return type;
    }

    public void setType(DocumentType type)
    {
        this.type = type;
    }

    public double getDownloadCash()
    {
        return downloadCash;
    }

    public void setDownloadCash(double downloadCash)
    {
        this.downloadCash = downloadCash;
    }

    public DocumentDirectoryBean getDirectory()
    {
        return this.directory;
    }

    public void setDirectory(DocumentDirectoryBean directory)
    {
        this.directory = directory;
    }

    @Override
    public BufferedImage getPreviewPicture()
    {
        return retrieveContent().getPreviewImage();
    }

    @Override
    public Date getUploadDate()
    {
        return this.uploadDate;
    }

    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public DocumentContent retrieveContent()
    {
        if (file == null) {
            file = new DocumentContent(name, getPrePicMd5());
            file.load(getContentMd5());
            file.loadPreviewImage(getPrePicMd5());
        }
        return file;
    }

    public String getContentMd5()
    {
        return contentMd5;
    }

    public void setContentMd5(String contentMd5)
    {
        this.contentMd5 = contentMd5;
    }

//    public void save()
//    {
//        repo.saveOrUpdate(this);
//    }

    public void save(DocumentType type, DocumentDirectoryBean directory)
    {
        this.setType(type);
        this.setDirectory(directory);
        save();
    }

//    @Override
//    public void remove()
//    {
//        repo.delete(this);
//    }

    @Override
    public long getId()
    {
        return super.getId();
    }

    public void setPrePicMd5(String prePicMd5)
    {
        this.prePicMd5 = prePicMd5;
    }

    public String getPrePicMd5()
    {
        return prePicMd5;
    }


    @Override
    public Member getUploader()
    {
        return this.uploader;
    }    
    public void setUploader(Member member){
        this.uploader=member;
    }

}
