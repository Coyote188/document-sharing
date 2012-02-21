package com.dss.storage.service;

import java.util.List;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.Document;

public interface DocumentService {

	/**
	 * search document methods
	 * @param strInDocument
	 * @return
	 */
	public List<DocumentBean> findDocWithStr(String strInDocument);

	public List<DocumentBean> findPosterDoc(Member poster);

	public List<DocumentBean> findLatestUploadedDoc();

	public List<DocumentBean> findMyDocs();

	public List<DocumentBean> findDocsByDirectory(DocumentDirectoryBean directory);

	public List<DocumentDirectoryBean> findAllDirectory();

}