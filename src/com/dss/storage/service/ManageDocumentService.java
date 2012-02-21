package com.dss.storage.service;

import java.util.List;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentContentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.model.DocumentDirectory;

public interface ManageDocumentService
{
    DocumentContentBean getDocumentContent(DocumentBean currentDocument);
    DocumentTypeBean getRootDocumentType();
    
    void updateDocument(DocumentBean data, DocumentTypeBean type, DocumentDirectoryBean directory);
	/**
	 * directory
	 * @return
	 */
	public DocumentDirectoryBean createDirectory();
	public void createDefaultDirectory(MemberBean member);
	public void createSubDirectory(DocumentDirectoryBean dir);
//	public void updateDirectory(DocumentDirectoryBean dir);
	public void updateDirectory(DocumentDirectoryBean parent,DocumentDirectoryBean dir);
	public void removeDirectory(DocumentDirectoryBean dir);
	public List<DocumentDirectoryBean> findAllDirectory();
	DocumentDirectoryBean getRootDocumentDirectory();
}
