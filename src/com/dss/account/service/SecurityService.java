package com.dss.account.service;

import java.util.Iterator;

import org.zkoss.zk.ui.Sessions;

import com.dss.account.bean.MemberBean;
import com.dss.account.control.CookieUtil;

public interface SecurityService {

	public boolean authenticate(MemberBean member);

//	public MemberBean getLoginMember(String token);

//	public boolean isLogin(String token);

	public MemberBean getLoginMember();

	public void loginOff();

	public boolean isLogin();

}