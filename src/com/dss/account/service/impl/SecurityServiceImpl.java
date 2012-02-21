package com.dss.account.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.dss.account.bean.MemberBean;
import com.dss.account.control.CookieUtil;
import com.dss.account.model.Member;
import com.dss.account.repository.CashAccountRepository;
import com.dss.account.repository.MemberRepository;
import com.dss.account.service.SecurityService;

@SuppressWarnings("unchecked")
@Scope("singleton")
@Component("SecurityService")
public class SecurityServiceImpl
        implements SecurityService
{

    // @Resource(name = "member")
    // private Member member;
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;
    @Resource(name = "cashAccountRepository")
    private CashAccountRepository cashAccountRepository;
    public Map<String, Member> loginMembers = new ConcurrentHashMap<String, Member>();

    // public List<String> tokens = new ArrayList<String>();

    public boolean authenticate(MemberBean member)
    {
        // Member loginedMember = this.member.authenticate(member);
        boolean isAuthenticateSuccess = false;
        Member loginedMember = memberRepository.getUserIfExist(member.getUsername());
        if (loginedMember != null) {
            if (loginedMember.isPasswordCorrect(member.getPassword())) {
                if (loginedMember.getCashAccount() != null) {
                    loginedMember.getCashAccount().setCashAccountRepository(cashAccountRepository);
                }
                UUID uuid = UUID.randomUUID();
                String a = uuid.toString();
                loginMembers.put(a, loginedMember);
                // tokens.add(a);
                // Session s = Sessions.getCurrent();
                // s.setAttribute("token", a);
                CookieUtil.setCookie("loginToken", a);
                isAuthenticateSuccess = true;
            }
        }
        return isAuthenticateSuccess;
    }

    public void loginOff()
    {
        loginMembers.remove(CookieUtil.getCookie("loginToken"));
        // tokens.remove("loginToken");
        // CookieUtil.removeToken();
    }

    // public MemberBean getLoginMember(String token) {
    // MemberBean member = (MemberBean) loginMembers.get(token);
    // if (member != null) {
    // return member;
    // }
    // return null;
    // }

    public MemberBean getLoginMember()
    {
        // MemberBean member = (MemberBean)
        // loginMembers.get(CookieUtil.getCookie("loginToken"));
        // return member;
        if (CookieUtil.getCookie("loginToken") == null)
            return null;
        else
            return loginMembers.get(CookieUtil.getCookie("loginToken"));
    }

    // public boolean isLogin(String token) {
    // Iterator it = tokens.iterator();
    // boolean result = false;
    // while (it.hasNext()) {
    // String m = (String) it.next();
    // if (m.equals(token)) {
    // result = true;
    // break;
    // }
    // }
    // return result;
    // }

    public boolean isLogin()
    {
        // Iterator it = tokens.iterator();
        // boolean result = false;
        // while (it.hasNext()) {
        // String m = (String) it.next();
        // if (m.equals(CookieUtil.getCookie("loginToken"))) {
        // result = true;
        // break;
        // }
        // }
        if (CookieUtil.getCookie("loginToken") == null)
            return false;
        else
            return loginMembers.containsKey(CookieUtil.getCookie("loginToken"));
        // return result;
    }
}
