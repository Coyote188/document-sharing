package com.dss.account.ui;

import java.util.Date;

import com.dss.account.bean.ProfileBean;
import com.dss.account.bean.MemberBean;

public class EditableProfile implements ProfileBean{
	
	private Date birthDate;
	private String contactMethods;
	private String gender;
	private String hobbie;
	private String location;
	private String realName;
	private String calledName;
	private MemberBean member;
	
	public EditableProfile(ProfileBean profile){
		this.birthDate = profile.getBirthDate();
		this.contactMethods = profile.getContactMethods();
		this.gender = profile.getGender();
		this.hobbie = profile.getHobbie();
		this.location = profile.getLocation();
		this.realName = profile.getRealName();
		this.calledName = profile.getCalledName();
	}
	public EditableProfile(){}
	@Override
	public Date getBirthDate() {
		// TODO Auto-generated method stub
		return birthDate;
	}

	@Override
	public String getCalledName() {
		// TODO Auto-generated method stub
		return calledName;
	}

	@Override
	public String getContactMethods() {
		// TODO Auto-generated method stub
		return contactMethods;
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}

	@Override
	public String getHobbie() {
		// TODO Auto-generated method stub
		return hobbie;
	}



	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public String getRealName() {
		// TODO Auto-generated method stub
		return realName;
	}


	public void setBirthDate(Date birthDate) {
		// TODO Auto-generated method stub
		this.birthDate = birthDate;
		
	}


	public void setCalledName(String calledName) {
		// TODO Auto-generated method stub
		this.calledName=calledName;
	}


	public void setContactMethods(String contactMethods) {
		// TODO Auto-generated method stub
		this.contactMethods = contactMethods;
	}


	public void setGender(String gender) {
		// TODO Auto-generated method stub
		this.gender = gender;
	}

	public void setHobbie(String hobbie) {
		// TODO Auto-generated method stub
		this.hobbie = hobbie;
	}

	

	public void setLocation(String location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

	public void setRealName(String realName) {
		// TODO Auto-generated method stub
		this.realName = realName;
	}
	
	public MemberBean getMember() {
		return member;
	}
	
	public void setMember(MemberBean member) {
		this.member=member;
	}
	@Override
	public Long getProfileId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
