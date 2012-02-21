package com.dss.account.ui;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.model.MailBox;
import com.dss.account.model.Profile;

public class EditableMember implements MemberBean {
	private Long id;
	private String password;
	private String username;
	private ProfileBean profile;
	private CashAccountBean cashAccount;
	private MailBox mailBox;

	public EditableMember() {}
	
	public EditableMember(MemberBean member){
		this.username = member.getUsername();
		this.password = member.getPassword();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ProfileBean getProfile() {
		return profile;
	}

	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}

	public CashAccountBean getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(CashAccountBean cashAccount) {
		this.cashAccount = cashAccount;
	}

	public MailBox getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	public boolean isPasswordCorrect(MemberBean member) {
		return false;
	}

	

	
}
