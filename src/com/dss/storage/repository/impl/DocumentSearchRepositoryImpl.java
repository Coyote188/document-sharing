package com.dss.storage.repository.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;
import com.dss.account.repository.impl.MemberRepositoryImpl;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.Document;
import com.dss.storage.repository.DocumentSearchRepository;

@SuppressWarnings({"unchecked","unused"})
@Repository("documentSearchRepository")
public class DocumentSearchRepositoryImpl extends HibernateDaoSupport implements DocumentSearchRepository{
	
	public static final Log log = LogFactory.getLog(MemberRepositoryImpl.class);
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	public List findByDocumentName(String strInDocumentName){
		try {
			String queryString = "from Document d where d.title like ? order by d.uploadDate desc";
			return getHibernateTemplate().find(queryString,"%"+strInDocumentName+"%");
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List findByPoster(Member poster){
		try {
			String queryString = "from Document d where d.poster = ?";
			return getHibernateTemplate().find(queryString,poster.getId());
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List findByUploadDate(){
		try {
			String queryString = "from Document d order by d.uploadDate asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List findByTags(String tags){
		try {
			List resultList;
			String queryString = "from Document d where d.tags like ?";
			List<Document> docs = getHibernateTemplate().find(queryString,"%"+tags+"%");
			
			return docs;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List<Document> findDocsByDirectory(DocumentDirectoryBean directory){
		try {
			String queryString = "from Document d where d.directory = ?";
			return getHibernateTemplate().find(queryString , directory);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List<DocumentDirectoryBean> findMemberDirectory(MemberBean member) {
		try {
			String queryString = "from DocumentDirectory d where d.member = ?";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
