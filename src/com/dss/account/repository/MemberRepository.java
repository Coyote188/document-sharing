package com.dss.account.repository;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.model.Member;

public interface MemberRepository {

	public void saveProfile(ProfileBean profile);

	public void saveMember(MemberBean member);

	public ProfileBean newProfile();

	public List findMemberByUsername(String username);

	public List isUsernameExist(String username);

	public ProfileBean getProfile(MemberBean member);

	public void updateProfile(ProfileBean profile);

	public Member newMember();
	Member getUserIfExist(String username);
	
}
