package com.dss.wanted.repository.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.bean.MemberBean;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.bean.WantedStatus;
import com.dss.wanted.model.Solution;
import com.dss.wanted.model.Wanted;
import com.dss.wanted.repository.WantedRepository;
@SuppressWarnings("unchecked")
@Transactional
public class WantedRepositoryImpl extends HibernateDaoSupport implements
		WantedRepository {

	public List<Wanted> findOutOfdateWanted() {

		return null;
	}

	public void closeWanted(WantedBean wanted){
		try {
			Wanted wanted2 = (Wanted) wanted;
			wanted2.setStatus(WantedStatus.closed);
			getHibernateTemplate().saveOrUpdate(wanted2);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Wanted> findAvailableWanted() {
		try {
			String queryString = "from Wanted w where w.status='opened'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Wanted> findPostedWanted(MemberBean poster) {
		try {
			String queryString = "from Wanted w where w.poster=?";
			return getHibernateTemplate().find(queryString, poster);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public void saveNewWanted(WantedBean aNewWanted) {
		try {
			getHibernateTemplate().persist(aNewWanted);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void editWanted(Wanted wanted) {
		try {
			getHibernateTemplate().merge(wanted);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public WantedBean showWanted(Long wantedId) {
//		return getHibernateTemplate().load(Wanted.class, wantedId);
		return (WantedBean) getHibernateTemplate().get("com.dss.wanted.model.Wanted", wantedId);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public WantedBean createWanted() {
		Date openDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(openDate);
		cal.add(Calendar.DATE, 7);
		Wanted wanted = new Wanted();
		wanted.setOpenDate(openDate);
		wanted.setCloseDate(cal.getTime());
		wanted.setStatus(WantedStatus.opened);
		return wanted;

	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public SolutionBean createSolution(){
		Date proposeDate = new Date();
		Solution solution = new Solution();
		solution.setProposeDate(proposeDate);
		
		return solution;
	}
	
	@Override
	public void saveSolution(SolutionBean solution){
		try {
//			getHibernateTemplate().save(solution);
			getHibernateTemplate().persist(solution);
			System.out.println("get wantedId in repository : "+solution.getWantedId());
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Solution> getWantedSolution(Long wantedId){
		try {
			String queryString = "from Solution s where s.wantedId = ? ";
//		 	return (List<Solution>) getHibernateTemplate().findByExample("com.dss.wanted.model.Wanted", 1);
			return getHibernateTemplate().find(queryString,wantedId);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Solution> getProposeSolution(Long proposeId){
		try {
			String queryString = "from Solution s where s.proposer=?";
			return (List<Solution>) getHibernateTemplate().find(queryString,proposeId);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
}
