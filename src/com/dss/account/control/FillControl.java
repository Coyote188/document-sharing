package com.dss.account.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;

@SuppressWarnings("serial")
@Component("fillControl")
@Scope("request")
public class FillControl extends GenericForwardComposer {
	@Resource
	MemberService memberService;
	@Resource
	SecurityService securityService;
	CashAccountBean cashAccount;
	private double money ;
	
	/**
	 * getter & setter
	 * @return
	 */

	public void setMoney(double money) {
		this.money = money;
	}


	public double getMoney() {
		return money;
	}
	
	
	public CashAccountBean getCashAccount() {
		return cashAccount;
	}


	public void setCashAccount(CashAccountBean cashAccount) {
		this.cashAccount = cashAccount;
	}

	public void onClick$btnFillSubmit(){
		memberService.fill(money);
	}
	

	@Override
	public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}



}
