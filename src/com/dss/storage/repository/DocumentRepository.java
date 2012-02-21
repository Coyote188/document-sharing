package com.dss.storage.repository;

import java.io.InputStream;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.model.Document;
import com.dss.storage.model.DocumentDirectory;

public interface DocumentRepository {

//	Document saveDocument(String member, DocumentBean beanData, InputStream content);

	Document newDocument();

	List<Document> findAllReleasedDocuments();

//	InputStream getFile(String documentId);
//	void update(Document data);

	boolean isDocumentExist(String md5);

	

}
