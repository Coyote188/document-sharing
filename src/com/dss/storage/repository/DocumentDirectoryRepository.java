package com.dss.storage.repository;


import java.util.List;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentDirectoryBean;

public interface DocumentDirectoryRepository {
	public DocumentDirectoryBean newDirectory();
	public DocumentDirectoryBean getDirectory(Member member);
//	List<DocumentDirectory> findSubDirectory(long id);
//	void addSubDirectory(DocumentDirectory documentDirectory,
//			DocumentDirectory subDirectory);
	public List<DocumentDirectoryBean> findDirByMember(Member member);
   
   
	
}
