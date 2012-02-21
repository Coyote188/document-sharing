package com.dss.account.model;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.MailBoxBean;
import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.repository.MemberRepository;
//import com.dss.storage.model.DocumentDirectory;

@SuppressWarnings("unchecked")
@Component("member")
public class Member implements MemberBean {

	private Long id;
	private String password;
	private String username;
	private Profile profile;
//	private DocumentDirectory directory;
	@Resource
	private CashAccount cashAccount;
	private MailBox mailBox;

	@Resource(name = "memberRepository")
	MemberRepository memberRepository;

//	public Member authenticate(MemberBean member) {
//		return (Member) isPasswordCorrect(member);
//	}

	public void inputData(MemberBean member) {
		this.id = member.getId();
		this.password = member.getPassword();
		this.username = member.getUsername();

	}

	public Member() {
	}



	 public void save(MemberBean member){
	 memberRepository.saveMember(this);
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
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public CashAccount getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(CashAccountBean cashAccount) {
		this.cashAccount = (CashAccount) cashAccount;
	}

	public MailBoxBean getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	

	public boolean isPasswordCorrect(String password) {
		
//		List members = memberRepository.isUsernameExist(member.getUsername());
//		MemberBean temp = null;
//		MemberBean memberLogined = null;
//		if (!members.isEmpty()) {
//			Iterator<Member> lt = members.iterator();
//			while (lt.hasNext()) {
//				temp = lt.next();
//				if (temp.getPassword().equals(member.getPassword())) {
//						memberLogined = temp;			
//				} 
//				break;
//			}
//		} else {
//			
//		}
//		return memberLogined;
	    
	    return getPassword().equals(password);
	    

	}
	
	public void reward(double cash){
		cashAccount.gainCash(cash, this);
	}

	
}
