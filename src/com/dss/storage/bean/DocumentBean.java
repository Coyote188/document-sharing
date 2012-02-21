package com.dss.storage.bean;

import java.awt.image.BufferedImage;
import java.util.Date;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;

public interface DocumentBean
{
    public long getId();

    public DocumentFormat getFormat();

    public String getName();

    public double getSize();

    public String getSimpleDescription();

    public String getTags();

    public String getTitle();

    public double getDownloadCash();

    public DocumentTypeBean getType();

    public DocumentDirectoryBean getDirectory();

    public Date getUploadDate();

    public BufferedImage getPreviewPicture();

    public void setFormat(DocumentFormat format);

    public void setName(String name);

    public void setSize(double size);

    public void setSimpleDescription(String desc);

    public void setTags(String tags);

    public void setTitle(String title);

    public void setDownloadCash(double downloadCash);

	public MemberBean getUploader();
}