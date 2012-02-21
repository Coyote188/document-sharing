package com.dss.storage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.model.Member;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.DocumentDirectory;
import com.dss.storage.repository.DocumentDirectoryRepository;
import com.dss.util.hibernate.HibernateRepository;
import com.dss.util.log.LogUtil;

@Repository("documentDirectoryRepository")
@SuppressWarnings("unchecked")
public class DocumentDirectoryRepositoryImpl
        extends HibernateRepository<DocumentDirectory>
        implements DocumentDirectoryRepository
{

    @Autowired
    public DocumentDirectoryRepositoryImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public DocumentDirectoryBean newDirectory()
    {
        return new DocumentDirectory();
    }
    
    @Transactional
    public void save(DocumentDirectoryBean directory){
    	try {
			getHibernateTemplate().persist(directory);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
    }
    
    @SuppressWarnings("null")
	public List<DocumentDirectoryBean> findDirByMember(Member member){
    	try {
    		String queryString = "from DocumentDirectory dir where dir.member =?";
    		List<DocumentDirectoryBean> temp = new ArrayList<DocumentDirectoryBean>();
    		List<DocumentDirectory> result = getHibernateTemplate().find(queryString,member);
    		System.out.println("test in document directory repository "+result.size());
    		for (DocumentDirectory dir : result ) {
				temp.add((DocumentDirectoryBean)dir);
			}
    		return temp;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
    }

	@Override
	public DocumentDirectoryBean getDirectory(Member member) {
		try {
			String str = "from DocumentDirectory dir where dir.parent is null and dir.member =?";
			List directoryList = getHibernateTemplate().find(str,member);
			return (DocumentDirectoryBean) directoryList.get(0);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
}
