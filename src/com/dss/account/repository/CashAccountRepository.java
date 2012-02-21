package com.dss.account.repository;

import java.util.List;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.model.CashAccount;
import com.dss.account.model.Member;

public interface CashAccountRepository {

	public void updateCashAccount(CashAccountBean cashAccount);
	public Double getCurrentPoint(Member member);
	public CashAccountBean getCashAccount(Member member);
	public CashAccountBean newCashAccount();
	public void save(CashAccountBean cashAccount);
//	public void updateCurrentPoint(Double point, Member member);
//	public void minusCurrentPoint(Double point, Member member);
	public List<CashAccount> findAll();

}