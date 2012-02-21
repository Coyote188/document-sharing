package com.dss.account.model;

import org.springframework.stereotype.Component;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.PaymentProviderBean;

@Component("paymentProvider")
public class PaymentProvider implements PaymentProviderBean {

	private Long providerId;
	private String name;
	private String homePage;
	public CashAccountBean cashAccount;

	

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public void setCashAccount(CashAccountBean cashAccount) {
		this.cashAccount = cashAccount;
	}

	public CashAccountBean getCashAccount() {
		return cashAccount;
	}



	
}
