package com.dss.storage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.testng.annotations.Test;
import com.dss.account.model.Member;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.Document;
import com.dss.storage.repository.DocumentSearchRepository;
import com.dss.storage.service.DocumentService;

@SuppressWarnings( { "unchecked", "null" })
@Service
@Test
public class SearchServiceImpl implements DocumentService {
	@Resource
	public MemberService memberService;
	@Resource
	private DocumentSearchRepository repo;
	@Resource
	private SecurityService securityService;

	/**
	 * find documents by str
	 */

	@Override
	public List findDocWithStr(String strInDocument) {
		return repo.findByDocumentName(strInDocument);
	}

	/**
	 * find documents by document'poster
	 */

	@Override
	public List findPosterDoc(Member poster) {
		return repo.findByPoster(poster);
	}

	/**
	 * find the login menber'documents
	 */

	@Override
	public List findMyDocs() {
		return repo.findByPoster((Member) securityService.getLoginMember());
	}

	/**
	 * find documents by directory
	 */

	@Override
	public List findDocsByDirectory(
			DocumentDirectoryBean directory) {
		List<Document> temp = repo
				.findDocsByDirectory((DocumentDirectoryBean) directory);
//		List<DocumentBean> result = null;
//		for (DocumentBean doc : temp) {
//			result.add(doc);
//		}
//		return result;
		return temp;
	}

	/**
	 * find the latest uploaded documents
	 */

	@Override
	public List findLatestUploadedDoc() {
//		List documentList = null;
//		List<Document> docList = repo.findByUploadDate();
//		for (DocumentBean doc : docList) {
//			documentList.add(doc);
//			if (documentList.size() > 10)
//				break;
//		}
//		return documentList;
	    return repo.findByUploadDate();
	}

	/**
	 * find document directory
	 */
	@Override
	public List<DocumentDirectoryBean> findAllDirectory() {
		// document.getDirectory();
		return null;
	}

}
