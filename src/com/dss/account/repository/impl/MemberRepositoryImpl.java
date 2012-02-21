package com.dss.account.repository.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.model.Member;
import com.dss.account.model.Profile;
import com.dss.account.repository.MemberRepository;

@SuppressWarnings("unchecked")
@Repository("memberRepository")
public class MemberRepositoryImpl extends HibernateDaoSupport implements
		MemberRepository {
//	@Autowired
//	public void init(SessionFactory factory) {
//	    setSessionFactory(factory);
//	}
	private ApplicationContext context;
	
	private static final Log log = LogFactory
			.getLog(MemberRepositoryImpl.class);
	/**
	 * 对从页面得到的信息进行转换
	 * @param profile
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private Profile toProfile(ProfileBean profile) {
		Profile p = new Profile();
		p.setBirthDate(profile.getBirthDate());
		p.setContactMethods(profile.getContactMethods());
		p.setGender(profile.getGender());
		p.setHobbie(profile.getHobbie());
		p.setLocation(profile.getLocation());
		p.setRealName(profile.getRealName());
		p.setCalledName(profile.getCalledName());
	    Member member = new Member();
	    member.setId(profile.getMember().getId());
	    p.setMember(member);
		return p;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List isUsernameExist(String username) {
		return findMemberByUsername(username);

	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Member getUserIfExist(String username) {
        return findMemberByUsername(username).size()==0? null: (Member) findMemberByUsername(username).get(0);

    }

	public void saveProfile(ProfileBean profile) {
		try {
			log.debug("save profile error");
			// profile.setId(789L);
			getHibernateTemplate().persist(profile);
			// getHibernateTemplate().saveOrUpdate(
			// "com.dss.account.model.Profile", toProfile(profile));
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void updateProfile(ProfileBean profile){
		try {
			log.debug("update profile error the foreign key = " + profile.getMember().getId());
			getHibernateTemplate().update(profile);
//			getHibernateTemplate().persist(profile);
		} catch (RuntimeException e) {
			log.error(e);
			// TODO: handle exception
			throw e;
		}
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ProfileBean getProfile(MemberBean member) {
		try {
			return (ProfileBean) getHibernateTemplate().get(
					"com.dss.account.model.Profile", member.getId());
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void saveMember(MemberBean member) {
		try {
			getHibernateTemplate().save(member);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Member newMember(){
		return new Member();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ProfileBean newProfile() {
		// TODO Auto-generated method stub
//		return new Profile();
		log.debug("getProfileBean error");
//		if(context.getBean(Profile.class) != null){
//			log.debug("get profile error");
//			return context.getBean(Profile.class);
//		}else {
//			System.out.println("return new Profile()");
			return new Profile();
//		}
	}

	List list;

	@Test
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List findMemberByUsername(String username) {
		log.debug("find member list by:" + username);
		try {
			String queryString = "from Member as m where m.username = ? ";
			list = getHibernateTemplate().find(queryString, username);
			System.out.println("the memberList size : " + list.size());
			return list;
		} catch (RuntimeException e) {
			// TODO: handle exception
			log.error("find by username failed", e);
			throw e;
		}
	}

}
