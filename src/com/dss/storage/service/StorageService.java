package com.dss.storage.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.bean.SearchOptions;

public interface StorageService {
    DocumentBean createDocument();  
	void upload(byte[] streamData, DocumentBean document);
	List<DocumentBean> findAllDocuments();
	InputStream download(DocumentBean document);
	InputStream download(List<DocumentBean> documents);
	SearchOptions<DocumentBean> getSearchOptions();	
}
