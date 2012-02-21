package com.dss.account.bean;


public interface PaymentProviderBean {

	public String getName();

	public String getHomePage();

	public Long getProviderId();

	public CashAccountBean getCashAccount();

}