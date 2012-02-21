package com.dss.storage.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ostermiller.util.CircularByteBuffer;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.SearchOptions;
import com.dss.storage.model.Document;
import com.dss.storage.model.DocumentContent;
import com.dss.storage.model.SearchOptionsImpl;
import com.dss.storage.repository.DocumentRepository;
import com.dss.storage.service.*;
import com.dss.util.log.LogUtil;

@Service
@SuppressWarnings("unchecked")
public class StorageServiceImpl
        implements StorageService
{

    @Resource
    private DocumentRepository documentRepo;

    @Override
    public DocumentBean createDocument()
    {
        return documentRepo.newDocument();
    }

    @Override
    @Transactional
    public void upload(byte[] fileSent, DocumentBean sentData)
    {
        // TODO get current log in member and set to document
        DocumentContent file= new DocumentContent(sentData.getName(), fileSent);
        String md5= file.save();
        if(documentRepo.isDocumentExist(md5)){
            file.unSave();
        }
        else{
            System.out.println("file: "+md5);
            System.out.println("preview picture: "+file.getPicMd5());
            Document document = (Document) sentData;
            document.setUploadDate(new Date());
            document.setContentMd5(md5);
            document.setPrePicMd5(file.getPicMd5());
            document.save();
        }
    }

    @Override
    public List<DocumentBean> findAllDocuments()
    {
        List result = documentRepo.findAllReleasedDocuments();
        return result;
    }

    @Override
    public InputStream download(DocumentBean data)
    {
        DocumentContent document = ((Document) data).retrieveContent();
        return document.getFile();
    }

    @Override
    public InputStream download(List<DocumentBean> documents)
    {
        final List<Document> documentList = convertDocumentBeanListToDocumentList(documents);
        final CircularByteBuffer cbb = new CircularByteBuffer(102400);
        new Thread(new Runnable() {
            public void run()
            {
                compressToOneFile(cbb, documentList);
            }
        }).start();
        return cbb.getInputStream();
    }

    private List<Document> convertDocumentBeanListToDocumentList(List<DocumentBean> list)
    {
//        List<Document> documentList = new ArrayList<Document>();
//        for (DocumentBean d : list) {
//            documentList.add((Document) d);
//        }
        List documentList = list;
        return documentList;
    }

    private void compressToOneFile(CircularByteBuffer cbb, List<Document> documentList)
    {
        ZipOutputStream zipFile = new ZipOutputStream(cbb.getOutputStream());
        byte[] buf = new byte[1024000];
        try {
            for (Document d : documentList) {
                ZipEntry entry = new ZipEntry(d.getName());
                zipFile.putNextEntry(entry);
                DocumentContent content=d.retrieveContent();
                InputStream file = content.getFile();
                int len = 0;
                while ((len = file.read(buf)) > 0) {
                    zipFile.write(buf, 0, len);
                }
                file.close();
                zipFile.closeEntry();
            }

            zipFile.close();
        } catch (IOException e) {
            LogUtil.error(getClass(), e.getCause());
        }

    }

    @Override
    public SearchOptions getSearchOptions()
    {        
        return new SearchOptionsImpl();
    }

}
