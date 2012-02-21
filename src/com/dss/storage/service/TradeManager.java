package com.dss.storage.service;

import java.io.InputStream;
import java.util.List;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;

public interface TradeManager {
    
    DocumentBean createDocument();  

	public boolean download(DocumentBean document);

	public boolean download(List<DocumentBean> documents);

	public void upload(byte[] docData, DocumentBean document);

}