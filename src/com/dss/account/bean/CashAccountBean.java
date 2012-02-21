package com.dss.account.bean;

import com.dss.account.model.Member;


public interface CashAccountBean {

	public double getCurrentPoint();
	public String getPaymentAccountId();
	public PaymentProviderBean getPaymentProvider();
	public Long getCashAccountId();
	public Member getMember();
	public void setMember(Member member);

}