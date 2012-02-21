package com.dss.account.bean;

public interface MemberBean {

	public Long getId();
	public String getPassword();
	public String getUsername();
	public ProfileBean getProfile();
	public CashAccountBean getCashAccount();
	public MailBoxBean getMailBox();
	

}