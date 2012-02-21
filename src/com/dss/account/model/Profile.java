package com.dss.account.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.repository.MemberRepository;
@Component("profile")
public class Profile implements ProfileBean {
	MemberRepository memberRepository;
	private Long profileId;
//	private Long memberId;
	private Member member;
	private Date birthDate;
	private String contactMethods;
	private String gender;
	private String hobbie;
	private String location;
	private String realName;
	private String calledName;
	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void save(){
		memberRepository.saveProfile(this);
	}

	public String getCalledName() {
		return calledName;
	}

	public void setCalledName(String calledName) {
		this.calledName = calledName;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getContactMethods() {
		return contactMethods;
	}

	public void setContactMethods(String contactMethods) {
		this.contactMethods = contactMethods;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbie() {
		return hobbie;
	}

	public void setHobbie(String hobbie) {
		this.hobbie = hobbie;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public void setMember(MemberBean member) {
		// TODO Auto-generated method stub
		this.member = (Member) member;
	}

	
}
