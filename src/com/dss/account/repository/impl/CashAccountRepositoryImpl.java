package com.dss.account.repository.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.model.CashAccount;
import com.dss.account.model.Member;
import com.dss.account.model.PaymentProvider;
import com.dss.account.repository.CashAccountRepository;

@Repository("cashAccountRepository")
@Transactional
public class CashAccountRepositoryImpl extends HibernateDaoSupport implements CashAccountRepository{
	
	public static final Log log = LogFactory.getLog(MemberRepositoryImpl.class);
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CashAccountBean beanToEntity(CashAccountBean cashAccount){
		CashAccount cAccount= new CashAccount();
		cAccount.setCashAccountId(cashAccount.getMember().getId());
		cAccount.setCurrentPoint(cashAccount.getCurrentPoint());
		cAccount.setMember(cashAccount.getMember());
		cAccount.setPaymentAccountId(cashAccount.getPaymentAccountId());
		cAccount.setPaymentProvider((PaymentProvider) cashAccount.getPaymentProvider());
		return cAccount;
	}
	/* (non-Javadoc)
	 * @see com.dss.account.repository.impl.CashAccount#updateCashAccount(com.dss.account.model.CashAccount)
	 */
	public void save(CashAccountBean cashAccount){
		try {
			log.debug("save cashAccount error !");
			this.getHibernateTemplate().persist(beanToEntity(cashAccount));
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	@Override
	public void updateCashAccount(CashAccountBean cashAccount){
		try {
			log.debug("updateCashAccount error !");
			System.out.println("test in cashaccount repository"+ cashAccount.getCurrentPoint());
			this.getHibernateTemplate().update(beanToEntity(cashAccount));			
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.dss.account.repository.impl.CashAccount#getCurrentPoint(com.dss.account.model.Member)
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Double getCurrentPoint(Member member){
		try {
			log.debug("getCurrentPoint error !");
			CashAccountBean cashAccount = (CashAccountBean) this.getHibernateTemplate().get("com.dss.account.model.CashAccount", member.getId());
			return cashAccount.getCurrentPoint();
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public List<CashAccount> findAll(){
		try {
			log.debug("load all cashaccount error !");
			String queryString = 
				"from CashAccount c order by c.currentPoint desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.dss.account.repository.impl.CashAccount#getCashAccount(com.dss.account.model.Member)
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CashAccountBean getCashAccount(Member member){
		try {
			log.debug("getCashAccount error !");
			return (CashAccountBean) this.getHibernateTemplate().get("com.dss.account.model.CashAccount", member.getId());
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
//	public void updateCurrentPoint(Double point,Member member){
//		CashAccount cashAccount = getCashAccount(member);
//		Double oldPoint = cashAccount.getCurrentPoint();
//		Double newPoint = oldPoint + point;
//		cashAccount.setCurrentPoint(newPoint);
//		updateCashAccount(cashAccount);
//	}
	
//	public void minusCurrentPoint(Double point,Member member){
//		CashAccount cashAccount = getCashAccount(member);
//		Double oldPoint = cashAccount.getCurrentPoint();
//		Double newPoint = oldPoint - point;
//		cashAccount.setCurrentPoint(newPoint);
//		updateCashAccount(cashAccount);
//	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CashAccountBean newCashAccount(){
		return new CashAccount();
	}
	
}
