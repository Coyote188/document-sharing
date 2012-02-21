package com.dss.account.bean;

import java.util.Date;

import com.dss.account.model.Member;

public interface ProfileBean {

	public Long getProfileId();
	public Date getBirthDate();
	public String getContactMethods();
	public String getGender();
	public String getHobbie();
	public String getLocation();
	public String getRealName();
	public String getCalledName();
	public MemberBean getMember();
	public void setMember(MemberBean member);
}