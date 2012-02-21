package com.dss.storage.repository;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.Document;

@SuppressWarnings("unchecked")
public interface DocumentSearchRepository {
	
	public List findByDocumentName(String strInDocumentName);

	public List findByPoster(Member poster);

	public List findByUploadDate();

	public List findByTags(String tags);

	public List<Document> findDocsByDirectory(DocumentDirectoryBean directory);
}
