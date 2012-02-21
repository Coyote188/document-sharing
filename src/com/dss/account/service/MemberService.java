package com.dss.account.service;

import java.util.List;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.model.Member;

public interface MemberService {

	public MemberBean newMember();

//	public boolean saveMember(MemberBean member);

	public void saveProfile(ProfileBean profile);

	public ProfileBean getProfile();

	public CashAccountBean getCashAccount();

	public ProfileBean newProfile();

	public void updateProfile(ProfileBean profile);

	public void fill(Double money);

	public boolean exchange(Double point);

	public CashAccountBean newCashAccount();

	public void saveCashAccount(CashAccountBean cashAccount);

	/**
	 * 下载文档接口
	 * 
	 * @param point
	 * @param documentOwner
	 * @return the cash is enough?
	 */
	public boolean downloadDocument(double point, Member documentOwner);

	public List<CashAccountBean> getAllCashAccount();

	public boolean useCash(Double point);

	public void gainCash(double point, Member documentOwner);

	public boolean registerAsMember(MemberBean member, ProfileBean profile);

}
